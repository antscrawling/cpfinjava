// Utility functions
function formatCurrency(amount) {
    return new Intl.NumberFormat('en-SG', {
        style: 'currency',
        currency: 'SGD'
    }).format(amount);
}

function formatDate(date) {
    return new Intl.DateTimeFormat('en-SG', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    }).format(new Date(date));
}

// Form validation
function validateForm(form) {
    const startDate = new Date(form.start_date.value);
    const endDate = new Date(form.end_date.value);
    const initialBalance = parseFloat(form.initial_balance.value);
    const interestRate = parseFloat(form.interest_rate.value);
    const monthlyContribution = parseFloat(form.monthly_contribution.value);

    if (endDate <= startDate) {
        alert('End date must be after start date');
        return false;
    }

    if (initialBalance < 0) {
        alert('Initial balance cannot be negative');
        return false;
    }

    if (interestRate < 0 || interestRate > 100) {
        alert('Interest rate must be between 0 and 100');
        return false;
    }

    if (monthlyContribution < 0) {
        alert('Monthly contribution cannot be negative');
        return false;
    }

    return true;
}

// Auto-dismiss alerts after 5 seconds
document.addEventListener('DOMContentLoaded', function() {
    const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
    alerts.forEach(function(alert) {
        setTimeout(function() {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // Add form validation to all forms
    const forms = document.querySelectorAll('form');
    forms.forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!validateForm(form)) {
                event.preventDefault();
            }
        });
    });

    // Add date validation
    const dateInputs = document.querySelectorAll('input[type="date"]');
    dateInputs.forEach(function(input) {
        input.addEventListener('change', function() {
            const startDate = document.getElementById('start_date');
            const endDate = document.getElementById('end_date');

            if (startDate && endDate && startDate.value && endDate.value) {
                if (new Date(endDate.value) <= new Date(startDate.value)) {
                    endDate.setCustomValidity('End date must be after start date');
                } else {
                    endDate.setCustomValidity('');
                }
            }
        });
    });

    // Add number input validation
    const numberInputs = document.querySelectorAll('input[type="number"]');
    numberInputs.forEach(function(input) {
        input.addEventListener('input', function() {
            if (this.value < 0) {
                this.setCustomValidity('Value cannot be negative');
            } else {
                this.setCustomValidity('');
            }
        });
    });
});

// Chart update function
function updateChart(labels, data) {
    const chart = Chart.getChart('simulationChart');
    if (chart) {
        chart.data.labels = labels;
        chart.data.datasets[0].data = data;
        chart.update();
    }
}

// Export functions
function exportToCSV(data) {
    const csvContent = "data:text/csv;charset=utf-8," + data;
    const encodedUri = encodeURI(csvContent);
    const link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "cpf_simulation.csv");
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

function exportToPDF() {
    // This would typically use a library like jsPDF
    alert('PDF export functionality will be implemented here.');
}

// Responsive table handling
function handleResponsiveTables() {
    const tables = document.querySelectorAll('table');
    tables.forEach(function(table) {
        const wrapper = document.createElement('div');
        wrapper.className = 'table-responsive';
        table.parentNode.insertBefore(wrapper, table);
        wrapper.appendChild(table);
    });
}

// Initialize responsive tables
document.addEventListener('DOMContentLoaded', handleResponsiveTables);