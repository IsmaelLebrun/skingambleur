document.addEventListener('DOMContentLoaded', () => {

    const LIMITS = {
        '50': 2,    // Vedette
        '20': 3,    // Super High
        '5': 3,     // High
        '2': 5,     // Medium
        '1.5': 6,   // Low
        '0.1': 3,   // Trash
        '0.5': 3    // Super Trash
    };

    const counters = {
        '50': 0,
        '20': 0,
        '5': 0,
        '2': 0,
        '1.5': 0,
        '0.1': 0,
        '0.5': 0
    };


    const resultsDiv = document.getElementById('search-results');
    const selectedSkins = document.getElementById('selected-skins');
    let priceInput = document.querySelector('#prix');
    let categoryInput = document.querySelector('#category');
    const skinSearch = document.querySelector('#skin-search');
    const radioContainer = document.createElement('div');
    let selectedSkinIds = [];
    let selectedRadioValue = '';

    // Caché la barre de recherche au chargement de la page
    showHideSkinSearchBar(priceInput);

    categoryInput.insertAdjacentElement('afterend', radioContainer);


    priceInput.addEventListener('change', handlePriceInput);

    const displayResults = (skins, multiplier) => {
        console.log(multiplier);
        resultsDiv.innerHTML = ''; // Videz les résultats pour éviter les doublons
        skins.forEach(skin => {
            if (!LIMITS[multiplier]) return; // Ignore les multiplicateurs invalides
            const div = document.createElement('div');
            div.className = 'result-item';
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.value = skin.id;
            checkbox.id = `skin-${skin.id}-${multiplier}`;
            checkbox.checked = selectedSkinIds.includes(skin.id);
            checkbox.name = "checkbox";
            checkbox.addEventListener('change', () => toggleSelection(skin, multiplier));

            const limit = LIMITS[multiplier];
            if (limit && counters[multiplier] >= limit) {
                checkbox.disabled = !checkbox.checked;
            }

            const label = document.createElement('label');
            label.htmlFor = `skin-${skin.id}-${multiplier}`;
            label.textContent = `${skin.name} ~ ${skin.suggested_price}$`;
            div.appendChild(checkbox);
            div.appendChild(label);
            resultsDiv.appendChild(div);
        });
    };
    const toggleSelection = (skin, multiplier) => {
        if (selectedSkinIds.includes(skin.id)) {
            // Retirer le skin
            selectedSkinIds = selectedSkinIds.filter(id => id !== skin.id);
            removeSelectedSkin(skin);
            counters[multiplier]--;
        } else {
            // Vérifier la limite avant d'ajouter
            if (counters[multiplier] < LIMITS[multiplier]) {
                selectedSkinIds.push(skin.id);
                addSelectedSkin(skin, multiplier);
                counters[multiplier]++;
            } else {
                alert(`Vous ne pouvez pas sélectionner plus de ${LIMITS[multiplier]} skins pour ce multiplicateur (x${multiplier})`);
                return;
            }
        }

        updateCheckboxStates(multiplier);
    };
    const addSelectedSkin = (skin, multiplier) => {
        const li = document.createElement('li');
        li.setAttribute('data-skin-id', skin.id);
        li.setAttribute('data-multiplier', multiplier); // Ajouter le multiplicateur comme attribut

        const textNode = document.createTextNode(skin.name + " x" + multiplier + " " + skin.suggested_price + "$");
        const removeButton = document.createElement('button');
        removeButton.textContent = 'Remove';
        removeButton.style.marginLeft = '10px';
        removeButton.addEventListener('click', () => {
            counters[multiplier]--;
            updateCheckboxStates(multiplier);
            const checkbox = document.querySelector(`input[type="checkbox"][value="${skin.id}"]`);
            if (checkbox) {
                checkbox.checked = false;
                checkbox.disabled = false;
            }
            selectedSkinIds = selectedSkinIds.filter(id => id !== skin.id);
            li.remove();
        });

        li.appendChild(textNode);
        li.appendChild(removeButton)
        selectedSkins.appendChild(li);
    };
    const removeSelectedSkin = (skin) => {
        const skinElement = document.querySelector(`[data-skin-id="${skin.id}"]`);
        if (skinElement) {
            const multiplier = skinElement.getAttribute('data-multiplier');
            skinElement.remove();
            updateCheckboxStates(multiplier);
            const checkbox = document.querySelector(`input[type="checkbox"][value="${skin.id}"]`);
            if (checkbox) {
                checkbox.checked = false;
                checkbox.disabled = false;
            }
        }
    };

    function handleSearchBarInput(radio) {
        const searchBar = document.getElementById('search-bar');
        let debounceTimeout;
        selectedRadioValue = document.querySelector('input[name="price-tier"]:checked').value;
        if(selectedRadioValue === radio.value) {
            const searchHandler = async () => {
                const query = searchBar.value.trim();
                resultsDiv.innerHTML = ''; // Efface les résultats avant de lancer une nouvelle recherche
                if (query.length > 0) {
                    try {
                        const response = await fetch(`/skins/search?price=${priceInput.value}&query=${query}&multiplier=${selectedRadioValue}`);
                        if (!response.ok) {
                            console.error(`HTTP error! status: ${response.status}`);
                        }
                        const data = await response.json();
                        displayResults(data, selectedRadioValue); // Passez le multiplicateur actuel à displayResults
                        radio.addEventListener('change', handleRadioChange);
                    } catch (error) {
                        console.error('Error fetching skins:', error);
                        resultsDiv.innerHTML = '<p>Une erreur est survenue lors de la recherche.</p>';
                    }
                } else {
                    resultsDiv.innerHTML = '<p>Aucun résultat.</p>';
                }
            };
            // Réinitialisez et ajoutez un gestionnaire d'événement avec debounce
            searchBar.removeEventListener('input', searchHandler);
            searchBar.addEventListener('input', () => {
                clearTimeout(debounceTimeout);
                debounceTimeout = setTimeout(searchHandler, 300);
            });
        }
    }

    function handlePriceInput(){
        const price = parseFloat(priceInput.value);
        if (price > 300) {
            disableCategoryOption('volatile');
        } else {
            enableCategoryOption('volatile');
        }
        if (price > 5) {
            disableCategoryOption('faible');
        } else {
            enableCategoryOption('faible');
        }
        resetCategoryOption();
        categoryInput.value = "default";
        categoryInput.addEventListener('change', handleRadioChange);
    }

    // category input handlers
    function disableCategoryOption(value) {
        const option = categoryInput.querySelector(`option[value="${value}"]`);
        if (option) option.disabled = true;
    }
    function enableCategoryOption(value) {
        const option = categoryInput.querySelector(`option[value="${value}"]`);
        if (option) option.disabled = false;
    }
    function resetCategoryOption() {
        const selectElement = document.getElementById('category');
        selectElement.value = 'default';
        hideForm();
    }
    function updateCheckboxStates(multiplier) {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            if (counters[multiplier] >= LIMITS[multiplier]) {
                checkbox.disabled = !checkbox.checked;
            } else {
                checkbox.disabled = false;
            }
        });
    }

    // gérer l'apparition des bouttons radios
    function handleRadioChange() {
        showHideSkinSearchBar(priceInput);
        setupRadioButtons(categoryInput.value);

        // Réinitialisez les compteurs et l'état des checkboxes
        Object.keys(counters).forEach(key => {
            counters[key] = 0;
        });
        selectedSkinIds = [];
        selectedSkins.innerHTML = '';
        resultsDiv.innerHTML = ''; // Videz les résultats de la recherche précédente

        const radios = document.querySelectorAll('input[name="price-tier"]');
        radios.forEach(radio => {
            radio.addEventListener('change', () => {
                if (priceInput.value && radio.checked) {
                    selectedRadioValue = radio.value;
                    const searchBar = document.getElementById('search-bar');
                    searchBar.value = '';
                    handleSearchBarInput(radio);
                }
            });
        });
        // s'il n'y a pas de changement de radio, alors la première est sélectionnée donc faire une rechercher avec la première
        if (selectedRadioValue) {
            const selectedRadio = document.querySelector(`input[name="price-tier"][value="${selectedRadioValue}"]`);
            handleSearchBarInput(selectedRadio);
        }
    }
    function setupRadioButtons(category) {
        const radioConfigs = {
            'volatile': [
                { label: 'Vedette (x50) 0.05%', value: '50' },
                { label: 'Super High (x20) 0.55%', value: '20' },
                { label: 'High (x5) 1.5%', value: '5' },
                { label: 'Medium (x2) 3.9%', value: '2' },
                { label: 'Low (x1.5) 19%', value: '1.5' },
                { label: 'Trash (x0.1) 75%', value: '0.1' },
            ],
            'moyen': [
                { label: 'Super High (x20)', value: '20' },
                { label: 'Low (x1.5)', value: '1.5' },
                { label: 'Trash (x0.7~3)', value: '0.5' },
                { label: 'Super Trash (x0.1)', value: '0.1' },
            ],
            'faible': [
                { label: 'Low (x1.5)', value: '1.5' },
                { label: 'Trash (x0.7~3)', value: '0.5' },
            ],
            'default': [],
        };
        const radios = radioConfigs[category] || [];
        updateRadioButtonsUI(radios);
    }
    function updateRadioButtonsUI(radios) {
        radioContainer.innerHTML = '';
        radios.forEach(radio => {
            const label = document.createElement('label');
            const input = document.createElement('input');
            input.type = 'radio';
            input.name = 'price-tier';
            input.value = radio.value;

            label.textContent = radio.label;
            label.appendChild(input);
            radioContainer.appendChild(label);
        });



        if (radios.length) {
            radioContainer.querySelector('input').checked = true; // Par défaut
            selectedRadioValue = radios[0].value;
        }
    }


    // afficher ou non la bdr
    function hideForm() {
        skinSearch.style.display = 'none';
        setupRadioButtons();
    }
    function showHideSkinSearchBar(input){
        if(input.value.length === 0){
            skinSearch.style.display = 'none';
        }else{
            skinSearch.style.display = 'block';
        }
    }

    // s'occuper de l'envoie du formulaire
    function collectFormData() {
        const caseName = document.getElementById("nom").value;
        const casePrice = parseFloat(priceInput.value);
        const caseCategory = document.getElementById("category").value;
        const skinsData = [];
        const skinsList = document.querySelectorAll('#selected-skins li');
        skinsList.forEach(li => {
            const text = li.childNodes[0].textContent.trim();
            skinsData.push(text);
        });
        return {
            name: caseName,
            price: casePrice,
            category: caseCategory,
            skins: skinsData
        };
    }
    function resetForm() {
        document.getElementById('nom').value = '';
        document.getElementById('prix').value = '';
        document.getElementById('category').value = 'default';
        document.getElementById('selected-skins').innerHTML = '';
    }
    async function sendFormData(data) {
        try {
            const response = await fetch('/caisse/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (response.ok) {
                const result = await response.json(); // Attendre la réponse booléenne
                if (result) {
                    alert('Caisse créée avec succès !');
                    resetForm();
                } else {
                    alert('Une erreur est survenue. La caisse n’a pas pu être créée.');
                }
            } else {
                alert('Erreur serveur lors de la création de la caisse.');
            }
        } catch (err) {
            console.error('Erreur réseau :', err);
            alert('Impossible de contacter le serveur.');
        }
    }
    const form = document.getElementById('caisse-form');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = collectFormData();
        await sendFormData(formData);
    });
});