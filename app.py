from flask import Flask, render_template, request, redirect, url_for, flash, session
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user
from werkzeug.security import generate_password_hash, check_password_hash
import bcrypt
import json
import os
from datetime import datetime
from pathlib import Path

app = Flask(__name__)
app.config['SECRET_KEY'] = os.urandom(24)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///cpf_simulation.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# Initialize extensions
db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'

# User model
class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True, nullable=False)
    password_hash = db.Column(db.String(120), nullable=False)
    secret_question = db.Column(db.String(200), nullable=False)
    secret_answer_hash = db.Column(db.String(120), nullable=False)

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

    def set_secret_answer(self, answer):
        self.secret_answer_hash = generate_password_hash(answer.lower())

    def check_secret_answer(self, answer):
        return check_password_hash(self.secret_answer_hash, answer.lower())

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))

# Routes
@app.route('/')
def index():
    if current_user.is_authenticated:
        return redirect(url_for('dashboard'))
    return render_template('index.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        secret_question = request.form.get('secret_question')
        secret_answer = request.form.get('secret_answer')

        user = User.query.filter_by(username=username).first()
        if user and user.check_password(password) and \
           user.secret_question == secret_question and \
           user.check_secret_answer(secret_answer):
            login_user(user)
            flash('Login successful!', 'success')
            return redirect(url_for('dashboard'))
        flash('Invalid credentials', 'error')
    return render_template('login.html')

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        secret_question = request.form.get('secret_question')
        secret_answer = request.form.get('secret_answer')

        if User.query.filter_by(username=username).first():
            flash('Username already exists', 'error')
            return redirect(url_for('register'))

        user = User(username=username, secret_question=secret_question)
        user.set_password(password)
        user.set_secret_answer(secret_answer)
        
        db.session.add(user)
        db.session.commit()
        
        flash('Registration successful! Please login.', 'success')
        return redirect(url_for('login'))
    return render_template('register.html')

@app.route('/dashboard')
@login_required
def dashboard():
    return render_template('dashboard.html')

@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('index'))

@app.route('/forgot-password', methods=['GET', 'POST'])
def forgot_password():
    if request.method == 'POST':
        username = request.form.get('username')
        secret_question = request.form.get('secret_question')
        secret_answer = request.form.get('secret_answer')
        new_password = request.form.get('new_password')

        user = User.query.filter_by(username=username).first()
        if user and user.secret_question == secret_question and user.check_secret_answer(secret_answer):
            user.set_password(new_password)
            db.session.commit()
            flash('Password reset successful! Please login with your new password.', 'success')
            return redirect(url_for('login'))
        flash('Invalid credentials', 'error')
    return render_template('forgot_password.html')

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True) 