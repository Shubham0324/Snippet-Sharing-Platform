        function send() {
            let show = false;

            let object = {
                "name": document.getElementById("myForm").elements[0].value,
                "email": document.getElementById("myForm").elements[1].value,
                "type": document.getElementById("myForm").elements[2].value,
                "pass": document.getElementById("myForm").elements[3].value,
                "code": document.getElementById("myForm").elements[4].value,
                "time": document.getElementById("myForm").elements[6].value,
                "views": document.getElementById("myForm").elements[5].value
            };
            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status === 200) {
                alert("Success!");
                show = true;
            } else {
                alert("There is some error in the project");
            }
            if (show === true) {
                showConfirmPage();
            }

        }
        function showConfirmPage() {
            window.open("confirm.html");

        }