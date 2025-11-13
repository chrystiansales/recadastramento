/**
 * Sistema de Gestão de Funcionários
 * Gerenciamento de cadastro, listagem e navegação
 */

// ===================================
// Estado da Aplicação
// ===================================
let employees = [];


// ===================================
// Dark Mode / Theme Toggle
// ===================================

/**
 * Inicializa o tema baseado na preferência salva do usuário
 */
function initializeTheme() {
    const savedTheme = localStorage.getItem('theme') || 'light';
    document.documentElement.setAttribute('data-theme', savedTheme);
    updateThemeToggleButton(savedTheme);
}

/**
 * Alterna entre tema claro e escuro
 */
function toggleTheme() {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';

    document.documentElement.setAttribute('data-theme', newTheme);
    localStorage.setItem('theme', newTheme);
    updateThemeToggleButton(newTheme);
}

/**
 * Atualiza o ícone e texto do botão de toggle
 */
function updateThemeToggleButton(theme) {
    const themeIcon = document.getElementById('themeIcon');
    const themeText = document.getElementById('themeText');

    if (themeIcon && themeText) {
        if (theme === 'dark') {
            themeIcon.textContent = '☀️';
            themeText.textContent = 'Light';
        } else {
            themeIcon.textContent = '🌙';
            themeText.textContent = 'Dark';
        }
    }
}


// ===================================
// Menu Mobile
// ===================================

/**
 * Alterna a visibilidade do menu em dispositivos móveis
 */
function toggleMobileMenu() {
    const menu = document.querySelector('.sidebar__menu');
    const footer = document.querySelector('.sidebar__footer');

    if (menu) {
        menu.classList.toggle('sidebar__menu--open');
    }
    if (footer) {
        footer.classList.toggle('sidebar__footer--open');
    }
}

/**
 * Fecha o menu mobile antes de navegar
 */
function closeMobileMenuAndNavigate(url) {
    const menu = document.querySelector('.sidebar__menu');
    const footer = document.querySelector('.sidebar__footer');

    // Fecha o menu
    if (menu) {
        menu.classList.remove('sidebar__menu--open');
    }
    if (footer) {
        footer.classList.remove('sidebar__footer--open');
    }

    // Pequeno delay para animação antes de navegar
    setTimeout(() => {
        window.location.href = url;
    }, 100);
}


// ===================================
// Navegação entre Telas
// ===================================

/**
 * Exibe o menu principal e oculta outras telas
 */
function showMenu() {
    toggleScreen('mainMenu', true);
    toggleScreen('registrationForm', false);
    toggleScreen('employeeListView', false);
    updateActiveTab('mainMenu');
}

/**
 * Exibe o formulário de cadastro
 */
function showRegistration() {
    toggleScreen('mainMenu', false);
    toggleScreen('registrationForm', true);
    toggleScreen('employeeListView', false);
    updateActiveTab('registrationForm');
}

/**
 * Exibe a lista de funcionários
 */
function showEmployeeList() {
    toggleScreen('mainMenu', false);
    toggleScreen('registrationForm', false);
    toggleScreen('employeeListView', true);
    renderEmployees();
    updateActiveTab('employeeListView');
}

/**
 * Atualiza a aba ativa na sidebar
 * @param {string} screenId - ID da tela ativa
 */
function updateActiveTab(screenId) {
    const items = document.querySelectorAll('.sidebar__item');
    items.forEach(item => {
        const itemScreen = item.getAttribute('data-screen');
        if (itemScreen === screenId) {
            item.classList.add('sidebar__item--active');
        } else {
            item.classList.remove('sidebar__item--active');
        }
    });
}

/**
 * Alterna a visibilidade de uma tela
 * @param {string} screenId - ID do elemento da tela
 * @param {boolean} show - Se true, mostra a tela; se false, oculta
 */
function toggleScreen(screenId, show) {
    const screen = document.getElementById(screenId);
    if (screen) {
        if (show) {
            screen.classList.remove('hidden');
        } else {
            screen.classList.add('hidden');
        }
    }
}

/**
 * Navega para uma tela específica via sidebar
 * @param {string} screenId - ID da tela de destino
 */
function navigateToScreen(screenId) {
    // Navega para a tela
    if (screenId === 'mainMenu') {
        showMenu();
    } else if (screenId === 'registrationForm') {
        showRegistration();
    } else if (screenId === 'employeeListView') {
        showEmployeeList();
    }
}


// ===================================
// Máscaras de Entrada
// ===================================

/**
 * Aplica máscara de CPF (000.000.000-00)
 * @param {string} value - Valor a ser formatado
 * @returns {string} Valor formatado
 */
function maskCPF(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }
    return value;
}

/**
 * Aplica máscara de telefone ((00) 00000-0000)
 * @param {string} value - Valor a ser formatado
 * @returns {string} Valor formatado
 */
function maskPhone(value) {
    value = value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{2})(\d)/, '($1) $2');
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }
    return value;
}

/**
 * Inicializa os event listeners para máscaras de entrada
 */
function initInputMasks() {
    const cpfInput = document.getElementById('cpf');
    const phoneInput = document.getElementById('phone');

    if (cpfInput) {
        cpfInput.addEventListener('input', function(e) {
            e.target.value = maskCPF(e.target.value);
        });
    }

    if (phoneInput) {
        phoneInput.addEventListener('input', function(e) {
            e.target.value = maskPhone(e.target.value);
        });
    }
}


// ===================================
// Gerenciamento de Funcionários
// ===================================

/**
 * Adiciona um novo funcionário ao array
 * @param {Object} employee - Objeto com dados do funcionário
 */
function addEmployee(employee) {
    employees.push(employee);
}

/**
 * Limpa o formulário de cadastro
 */
function clearForm() {
    const form = document.getElementById('employeeForm');
    if (form) {
        form.reset();
    }
}

/**
 * Renderiza a lista de funcionários na tela
 */
function renderEmployees() {
    const list = document.getElementById('employeesList');

    if (!list) return;

    if (employees.length === 0) {
        list.innerHTML = '<div class="empty-state">Nenhum funcionário cadastrado ainda</div>';
        return;
    }

    list.innerHTML = employees.map((emp, index) => `
        <article class="employee-card">
            <p class="employee-card__info">
                <span class="employee-card__label">Nome:</span> ${escapeHtml(emp.name)}
            </p>
            <p class="employee-card__info">
                <span class="employee-card__label">CPF:</span> ${escapeHtml(emp.cpf)}
            </p>
            <p class="employee-card__info">
                <span class="employee-card__label">Telefone:</span> ${escapeHtml(emp.phone)}
            </p>
        </article>
    `).join('');
}

/**
 * Escapa caracteres HTML para prevenir XSS
 * @param {string} text - Texto a ser escapado
 * @returns {string} Texto escapado
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}


// ===================================
// Feedback Visual
// ===================================

/**
 * Exibe feedback visual no botão de cadastro
 * @param {HTMLElement} button - Elemento do botão
 */
function showSuccessFeedback(button) {
    const originalText = button.textContent;
    const originalBackground = button.style.background;

    button.textContent = '✓ Cadastrado!';
    button.style.background = '#28a745';

    setTimeout(() => {
        button.textContent = originalText;
        button.style.background = originalBackground;

        // Perguntar se quer voltar ao menu
        if (confirm('Funcionário cadastrado com sucesso! Deseja voltar ao menu?')) {
            showMenu();
        }
    }, 1500);
}


// ===================================
// Inicialização e Event Listeners
// ===================================

/**
 * Inicializa o formulário de cadastro
 */
function initEmployeeForm() {
    const form = document.getElementById('employeeForm');

    if (!form) return;

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // Coleta os dados do formulário
        const name = document.getElementById('name').value;
        const cpf = document.getElementById('cpf').value;
        const phone = document.getElementById('phone').value;

        // Cria o objeto do funcionário
        const employee = { name, cpf, phone };

        // Adiciona ao array
        addEmployee(employee);

        // Limpa o formulário
        clearForm();

        // Feedback visual
        const submitButton = e.target.querySelector('.btn--primary');
        if (submitButton) {
            showSuccessFeedback(submitButton);
        }
    });
}

/**
 * Inicializa a aplicação quando o DOM estiver pronto
 */
function init() {
    initializeTheme();
    initInputMasks();
    initEmployeeForm();
}

// Inicializa quando o DOM estiver carregado
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
} else {
    init();
}
