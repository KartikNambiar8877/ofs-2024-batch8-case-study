function loginCustomer(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const jsonData = {
        username: username,
        password: password
    };

    fetch("http://localhost:8080/customer/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        if (response.status === 200) {
            return response.json(); 
        } else if (response.status === 403) {
            alert("Account is blocked");
            throw new Error('Account is blocked');
        } else if (response.status === 401) {
            alert("Invalid credentials");
            throw new Error('Invalid credentials');
        } else {
            throw new Error('Login failed');
        }
    })
    .then(customer => {
        document.getElementById("customerDetails").innerHTML = `
            <h3>Welcome, ${customer.fName} ${customer.lName}</h3>
            <p><strong>Username:</strong> ${customer.username}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Phone:</strong> ${customer.phone}</p>
            <p><strong>Address:</strong> ${customer.addressLine1}, ${customer.city}, ${customer.state}, ${customer.zip}</p>
        `;
        alert("Login successful");
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

    const jsonData = {
        fName: fName,
        lName: lName,
        username: username,
        password: password,
        addressLine1: addressLine1,
        addressLine2: addressLine2,
        addressLine3: addressLine3,
        city: city,
        state: state,
        zip: zip,
        phone: phone,
        email: email,
        status: 'A'
    };

    fetch("http://localhost:8080/customer/addcustomer", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
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


function redirectToLogin() {
    window.location.href = "login.html"; 
}