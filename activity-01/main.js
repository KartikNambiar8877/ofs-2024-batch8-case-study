const fs = require('fs');
const readline = require('readline');
const filePath = './sample.json';
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});


function addItem(newItem) {
    fs.readFile(filePath, 'utf8', (err, data) => {
        if (err) {
            console.error('Error reading file:', err);
            rl.close();
            return;
        }

        
        let json = JSON.parse(data);

       
        json.items.push(newItem);

        
        fs.writeFile(filePath, JSON.stringify(json, null, 2), 'utf8', (err) => {
            if (err) {
                console.error('Error writing file:', err);
            } else {
                console.log('Item added successfully!');
            }
            rl.close();
        });
    });
}

function promptUser() {
    rl.question('Enter item ID: ', (id) => {
        rl.question('Enter item name: ', (name) => {
            rl.question('Enter item value: ', (value) => {
                
                const newItem = { id: parseInt(id, 10), name: name, value: value };

                
                addItem(newItem);
            });
        });
    });
}

promptUser();
