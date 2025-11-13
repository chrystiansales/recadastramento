# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Sistema de Gestão de Funcionários - A web application for employee management with registration, listing, and navigation features. Built with vanilla JavaScript, HTML5, and CSS3.

## Project Structure

```
recadastramento/
├── assets/
│   ├── css/
│   │   └── styles.css         # Organized CSS with CSS variables and BEM methodology
│   ├── js/
│   │   └── app.js             # JavaScript logic for app functionality
│   └── images/                # Image assets (currently empty)
├── pages/
│   ├── dados-pessoais.html    # Personal data form page
│   └── contatos.html          # Contacts list page
├── index.html                 # Home page with CCM logo
├── README.md                  # Project documentation
├── CLAUDE.md                  # This file - Claude Code guidance
└── .gitignore                 # Git ignore patterns
```

## Architecture & Code Organization

### CSS Architecture
- **CSS Variables (Design Tokens)**: All colors, spacing, fonts, and other design values are defined as CSS custom properties in `:root`
- **BEM Methodology**: Class naming follows Block__Element--Modifier pattern for better maintainability
- **Modular Organization**: Styles are organized into logical sections (Reset, Components, Utilities, etc.)
- **Responsive Design**: Mobile-first approach with media queries for different screen sizes

### JavaScript Architecture
- **Separation of Concerns**: Code is organized into logical sections (State, Navigation, Masks, Employee Management, etc.)
- **Pure Functions**: Most functions are pure and reusable
- **XSS Prevention**: `escapeHtml()` function sanitizes user input before rendering
- **Event Delegation**: Proper use of event listeners with initialization on DOMContentLoaded

### HTML Structure
- **Semantic HTML5**: Uses appropriate semantic tags (`<header>`, `<nav>`, `<section>`, `<article>`)
- **Accessibility**: Includes ARIA labels and attributes for screen readers
- **SEO**: Meta description and proper title tags

## Key Features

1. **Home Page**: Clean landing page with animated CCM logo
2. **Sidebar Navigation**: Fixed left sidebar with all system sections
3. **Employee Registration**: Comprehensive form with personal data fields
4. **Contact Management**: List and manage employee contacts
5. **Input Masks**: Automatic formatting for CPF (000.000.000-00) and Phone ((00) 00000-0000)
6. **Multi-page Architecture**: Separate HTML files for each section

## Development Workflow

This is a static web application with no build process. To view and develop:

1. Open `index.html` in a web browser, or
2. Use a local web server:
   - Python: `python -m http.server`
   - VS Code: Use Live Server extension
   - Node.js: `npx http-server`

## Code Style Guidelines

- **CSS**: Use BEM naming convention, organize by component, leverage CSS variables
- **JavaScript**: Use descriptive function names, add JSDoc comments, follow ES6+ standards
- **HTML**: Use semantic tags, include accessibility attributes, maintain proper indentation

## Notes

- Data is stored in memory only (resets on page reload)
- No backend or database integration currently
- Two menu options (Search and Reports) are marked as "in development"
