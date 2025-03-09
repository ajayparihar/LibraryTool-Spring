// Theme Toggle
function initThemeToggle() {
    const themeToggle = document.querySelector('.theme-toggle');
    const prefersDarkScheme = window.matchMedia('(prefers-color-scheme: dark)');
    
    // Set initial theme
    document.body.setAttribute('data-theme', 
        localStorage.getItem('theme') || 
        (prefersDarkScheme.matches ? 'dark' : 'light')
    );

    themeToggle.addEventListener('click', () => {
        const currentTheme = document.body.getAttribute('data-theme');
        const newTheme = currentTheme === 'light' ? 'dark' : 'light';
        document.body.setAttribute('data-theme', newTheme);
        localStorage.setItem('theme', newTheme);
    });
}

// Copy Code Button
function initCodeCopy() {
    document.querySelectorAll('.code-example').forEach(block => {
        const button = document.createElement('button');
        button.className = 'copy-button';
        button.textContent = 'Copy';
        block.appendChild(button);

        button.addEventListener('click', async () => {
            const code = block.querySelector('code').textContent;
            await navigator.clipboard.writeText(code);
            button.textContent = 'Copied!';
            setTimeout(() => {
                button.textContent = 'Copy';
            }, 2000);
        });
    });
}

// Search Functionality
function initSearch() {
    const searchInput = document.getElementById('doc-search');
    const searchResults = document.querySelector('.search-results');
    
    searchInput.addEventListener('input', debounce(async (e) => {
        const query = e.target.value;
        if (query.length < 2) {
            searchResults.style.display = 'none';
            return;
        }

        const results = await searchDocumentation(query);
        displaySearchResults(results);
    }, 300));
}

async function searchDocumentation(query) {
    // This would be replaced with actual search implementation
    // For now, returning mock results
    return [
        { title: 'Getting Started', url: '#getting-started' },
        { title: 'API Reference', url: '#api-reference' }
    ];
}

function displaySearchResults(results) {
    const searchResults = document.querySelector('.search-results');
    searchResults.innerHTML = '';
    
    results.forEach(result => {
        const link = document.createElement('a');
        link.href = result.url;
        link.textContent = result.title;
        searchResults.appendChild(link);
    });
    
    searchResults.style.display = 'block';
}

// Interactive Diagrams
function initDiagrams() {
    const diagrams = document.querySelectorAll('.interactive-diagram');
    diagrams.forEach(diagram => {
        let scale = 1;
        let isDragging = false;
        let startX, startY, translateX = 0, translateY = 0;
        const svg = diagram.querySelector('svg');
        const content = svg.querySelector('.diagram-content');

        // Initialize transform
        content.style.transform = `translate(0px, 0px) scale(1)`;

        // Zoom functions
        function setTransform() {
            content.style.transform = `translate(${translateX}px, ${translateY}px) scale(${scale})`;
        }

        function handleZoom(delta, mouseX, mouseY) {
            const oldScale = scale;
            scale = Math.min(Math.max(0.5, scale + delta), 3);
            
            // Adjust position to zoom towards mouse cursor
            if (oldScale !== scale) {
                const svgRect = svg.getBoundingClientRect();
                const x = mouseX - svgRect.left;
                const y = mouseY - svgRect.top;
                
                translateX += (x - translateX) * (1 - scale/oldScale);
                translateY += (y - translateY) * (1 - scale/oldScale);
                
                setTransform();
            }
        }

        // Mouse wheel zoom
        diagram.addEventListener('wheel', (e) => {
            e.preventDefault();
            const delta = -e.deltaY * 0.001;
            handleZoom(delta, e.clientX, e.clientY);
        });

        // Pan functionality
        diagram.addEventListener('mousedown', (e) => {
            isDragging = true;
            startX = e.clientX - translateX;
            startY = e.clientY - translateY;
            diagram.style.cursor = 'grabbing';
        });

        window.addEventListener('mousemove', (e) => {
            if (!isDragging) return;
            
            translateX = e.clientX - startX;
            translateY = e.clientY - startY;
            setTransform();
        });

        window.addEventListener('mouseup', () => {
            isDragging = false;
            diagram.style.cursor = 'grab';
        });

        // Control buttons
        diagram.querySelector('.zoom-in').addEventListener('click', () => {
            const rect = svg.getBoundingClientRect();
            handleZoom(0.1, rect.width/2, rect.height/2);
        });
        
        diagram.querySelector('.zoom-out').addEventListener('click', () => {
            const rect = svg.getBoundingClientRect();
            handleZoom(-0.1, rect.width/2, rect.height/2);
        });

        diagram.querySelector('.center-btn').addEventListener('click', () => {
            const rect = svg.getBoundingClientRect();
            translateX = rect.width/2 - content.getBBox().width/2;
            translateY = rect.height/2 - content.getBBox().height/2;
            setTransform();
        });
        
        diagram.querySelector('.reset').addEventListener('click', () => {
            scale = 1;
            translateX = 0;
            translateY = 0;
            setTransform();
        });

        // Initial centering
        const rect = svg.getBoundingClientRect();
        translateX = rect.width/2 - content.getBBox().width/2;
        translateY = rect.height/2 - content.getBBox().height/2;
        setTransform();
    });
}

// API Testing
function initApiTesting() {
    document.querySelectorAll('.endpoint-testing').forEach(endpoint => {
        const tryButton = endpoint.querySelector('.try-it-out');
        const requestBuilder = endpoint.querySelector('.request-builder');
        
        tryButton.addEventListener('click', () => {
            requestBuilder.style.display = 
                requestBuilder.style.display === 'none' ? 'block' : 'none';
        });
        
        endpoint.querySelector('.send-request').addEventListener('click', async () => {
            const response = await sendApiRequest(endpoint);
            displayApiResponse(endpoint, response);
        });
    });
}

async function sendApiRequest(endpoint) {
    // This would be replaced with actual API call
    // For now, returning mock response
    return {
        status: 200,
        data: { message: 'Success' }
    };
}

function displayApiResponse(endpoint, response) {
    const responseViewer = endpoint.querySelector('.response-viewer code');
    responseViewer.textContent = JSON.stringify(response, null, 2);
}

// Version Selector
function initVersionSelector() {
    const versionSelector = document.querySelector('.version-selector select');
    versionSelector.addEventListener('change', (e) => {
        // This would be replaced with actual version switching logic
        console.log(`Switching to version: ${e.target.value}`);
    });
}

// Utility Functions
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Initialize all features
document.addEventListener('DOMContentLoaded', () => {
    initThemeToggle();
    initCodeCopy();
    initSearch();
    initDiagrams();
    initApiTesting();
    initVersionSelector();
}); 