const baseUrl = "http://localhost:8080/autor/";

async function fetchAuthorById(id) {
    try {
        const response = await fetch(baseUrl + id);
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }
        return await response.json();
    } catch (error) {
        alert(error.message);
    }
    return null;
}

function displayAuthorInfo(author) {
    if (author) {
        document.getElementById('infoID').innerText = author.id;
        document.getElementById('editName').value = author.name;
        document.getElementById('editEmail').value = author.email;
        document.getElementById('editPhone').value = author.phone;

        document.getElementById('autorInfo').style.display = 'block';
    }
}


async function updateAuthor(id, updatedAuthor) {
    try {
        const response = await fetch(baseUrl + 'update/' + id, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedAuthor),
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }
        return await response.json();
    } catch (error) {
        alert(error.message);
    }
    return null;
}

function handleUpdateButtonClick() {
    const id = document.getElementById('infoID').innerText;
    const updatedName = document.getElementById('editName').value;
    const updatedEmail = document.getElementById('editEmail').value;
    const updatedPhone = document.getElementById('editPhone').value;

    const updatedAuthor = {
        name: updatedName,
        email: updatedEmail,
        phone: updatedPhone,
    };

    updateAuthor(id, updatedAuthor)
        .then((updatedAuthor) => {
            if (updatedAuthor) {
                displayAuthorInfo(updatedAuthor);
                showMessage('El autor ha sido modificado correctamente.', 'success');
            }
        })
        .catch((error) => {
            showMessage('No se pudo modificar el autor. Por favor, inténtelo de nuevo.', 'error');
        });
}

function showMessage(message, type) {
    const updateMessage = document.getElementById('updateMessage');
    updateMessage.innerText = message;
    updateMessage.style.display = 'block';
    updateMessage.style.color = type === 'success' ? 'green' : 'red';

    setTimeout(() => {
        updateMessage.style.display = 'none';
    }, 3000);
}


async function deleteAuthor(id) {
    try {
        const response = await fetch(baseUrl + id, {
            method: 'DELETE',
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }
        return true;
    } catch (error) {
        alert(error.message);
    }
    return false;
}

function handleDeleteButtonClick() {
    const id = document.getElementById('infoID').innerText;

    deleteAuthor(id)
        .then((deleted) => {
            if (deleted) {
                document.getElementById('autorInfo').style.display = 'none';
                showMessage('El autor ha sido eliminado correctamente.', 'success');
            }
        })
        .catch((error) => {
            showMessage('No se pudo eliminar el autor. Por favor, inténtelo de nuevo.', 'error');
        });
}


function main() {
    const searchButton = document.getElementById('searchButton');
    searchButton.addEventListener('click', async () => {
        const id = document.getElementById('autorID').value;
        if (id) {
            const author = await fetchAuthorById(id);
            displayAuthorInfo(author);
        } else {
            alert('Por favor, introduzca un ID de autor válido.');
        }
    });
    const updateButton = document.getElementById('updateButton');
    updateButton.addEventListener('click', handleUpdateButtonClick);
    const deleteButton = document.getElementById('deleteButton');
    deleteButton.addEventListener('click', handleDeleteButtonClick);
}



main();
