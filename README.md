# Sistema de Recadastramento - CCM

Sistema web para atualização cadastral de funcionários municipais.

## 🚀 Demo Online

Acesse o sistema: **[https://chrystiansales.github.io/recadastramento/](https://chrystiansales.github.io/recadastramento/)**

## 📋 Sobre o Projeto

Sistema de gestão de funcionários desenvolvido para facilitar o processo de atualização cadastral. Interface moderna, responsiva e intuitiva.

### ✨ Funcionalidades

- 🏠 **Home**: Página inicial com logo animado CCM
- 👤 **Dados Pessoais**: Formulário completo de cadastro com validação
- 📞 **Contatos**: Gerenciamento de contatos dos colaboradores
- 📱 **Totalmente Responsivo**: Funciona em desktop, tablet e celular
- 🎨 **Design Moderno**: Interface limpa com sidebar navegável

### 🛠️ Tecnologias

- HTML5
- CSS3 (com CSS Variables e BEM)
- JavaScript (Vanilla)

## 📱 Recursos Mobile

- Menu hambúrguer para navegação em dispositivos móveis
- Layout adaptativo para todos os tamanhos de tela
- Sidebar colapsável com navegação fluida

## 🎯 Funcionalidades Implementadas

### Formulário de Dados Pessoais
- ✅ CPF com máscara automática (000.000.000-00)
- ✅ Telefone com máscara automática ((00) 00000-0000)
- ✅ Validação de campos obrigatórios
- ✅ Seleção de raça/cor, sexo, nacionalidade
- ✅ Data de nascimento
- ✅ Estado e cidade de nascimento

### Sistema de Navegação
- ✅ Sidebar com menu organizado por categorias
- ✅ Menu mobile com animação suave
- ✅ Indicador visual de página ativa
- ✅ Botões de ação contextuais

## 🚀 Como Usar Localmente

1. Clone o repositório:
```bash
git clone https://github.com/chrystiansales/recadastramento.git
```

2. Abra o arquivo `index.html` em seu navegador, ou use um servidor local:

**Python:**
```bash
python -m http.server
```

**Node.js:**
```bash
npx http-server
```

**VS Code:**
- Instale a extensão "Live Server"
- Clique com botão direito em `index.html` → "Open with Live Server"

## 📂 Estrutura do Projeto

```
.
├── index.html                  # Página inicial
├── form-dados-pessoais.html   # Formulário de dados pessoais
├── form-contatos.html         # Página de contatos
├── styles.css                  # Estilos com BEM e CSS Variables
├── app.js                      # Lógica JavaScript
├── CLAUDE.md                   # Documentação técnica
└── README.md                   # Este arquivo
```

## 🎨 Design

- **Metodologia CSS**: BEM (Block Element Modifier)
- **Design Tokens**: CSS Variables para cores, espaçamentos e fontes
- **Tema**: Azul institucional com degradês
- **Tipografia**: Segoe UI / Tahoma / Geneva

## 📝 Roadmap

Funcionalidades planejadas:
- [ ] Estado Civil
- [ ] Dependentes
- [ ] Endereços
- [ ] Escolaridades
- [ ] Cursos
- [ ] Registros Profissionais
- [ ] Declarações
- [ ] Prova de Vida
- [ ] Documentos

## 📄 Licença

Este projeto está sob a licença MIT.

## 👨‍💻 Autor

**Chrystian Sales**

- GitHub: [@chrystiansales](https://github.com/chrystiansales)
- Projeto: [recadastramento](https://github.com/chrystiansales/recadastramento)

---

🤖 Desenvolvido com auxílio de [Claude Code](https://claude.com/claude-code)
