var url = "http://localhost:8080/autor";

async function postContactMessage(name, email, phone) {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: `{
                "name": "${name}",
                "email": "${email}",
                "phone": "${phone}"
            }`,
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }
    } catch (error) {
        return error.message;
    }
    return null;
}

function handleFormSubmit() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('mail').value;
    const phone = document.getElementById('phone').value;

    postContactMessage(name, email, phone)
        .then((error) => {
            if (error) {
                alert(error);
            } else {
                alert('Message sent successfully');
                // navigate to home page
                window.location.href = './index.html';

            }
        });
    }


function main() {

        const form = document.getElementById("contact-form");
        const submitButton = document.getElementById("submit-contact-form");
        submitButton.addEventListener("click", async function (event) {
            if (!form.checkValidity()) {
                return false;
            }
    
            event.preventDefault();
            event.stopPropagation();

            handleFormSubmit();

        });
}

main();