function loginCustomer(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const xmlData = `<customer>
                        <username>${username}</username>
                        <password>${password}</password> <!-- Update to match database column -->
                     </customer>`;

    fetch("http://localhost:8080/BankApp/CustomerCRUDMain", {
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml'
        },
        body: xmlData
    })
    .then(response => {
        console.log(response)
        if (response.status == 200) {
            alert("Login successful");
        } else if (response.status === 403) {
            alert("Account is blocked");
        } else if (response.status === 401) {
            alert("Invalid credentials");
        } else {
            throw new Error('Login failed');
        }
    })
    .catch(error => {
        console.error("Error during login:", error);
        alert('Error during login: ' + error.message);
    });
}

function registerCustomer(event) {
    event.preventDefault();
    
    const fName = document.getElementById("fName").value;
    const lName = document.getElementById("lName").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const addressLine1 = document.getElementById("addressLine1").value;
    const addressLine2 = document.getElementById("addressLine2").value;
    const addressLine3 = document.getElementById("addressLine3").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const zip = document.getElementById("zip").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;

    const xmlData = `<customer>
                        <fName>${fName}</fName>
                        <lName>${lName}</lName>
                        <username>${username}</username>
                        <password>${password}</password>
                        <addressLine1>${addressLine1}</addressLine1>
                        <addressLine2>${addressLine2}</addressLine2>
                        <addressLine3>${addressLine3}</addressLine3>
                        <city>${city}</city>
                        <state>${state}</state>
                        <zip>${zip}</zip>
                        <phone>${phone}</phone>
                        <email>${email}</email>
                        <status>A</status>
                    </customer>`;

    fetch("http://localhost:8080/BankApp/CustomerRegister", {
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml'
        },
        body: xmlData
    })
    .then(response => {
        console.log(response);
        if (response.status == 200) {
            alert("Registration successful");
        } else {
            throw new Error('Registration failed');
        }
    })
    .catch(error => {
        console.error("Error during registration:", error);
        alert('Error during registration: ' + error.message);
    });
}

