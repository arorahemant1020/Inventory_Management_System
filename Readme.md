Project: Inventory Management System
By:
    Name: Hemant Arora
    Roll No.: 22CSU270
    Section: Full Stack-B

The project consists of 2 Micro Services (both services with all endpoints fully functioning and working). 
    1. Product 
        -API END POINTS
            1. /product/all: view the list of all products
            2. /product/{pid}: view product by pid
            3. /product/add: add a product 
                JSON for add: 
                    {
                        "name":"XYZ",
                        "price":100,
                        "stock":300,
                        "supplier_id":2
                    }

            4. /product/update/{pid}:update a product (same json)
            5. /product/remove/{pid}:delete a product from db
    2. Supplier
    -API END POINTS
            1. /supplier/: view the list of all suppliers
            2. /supplier/{sid}: view supplier by sid
            3. /supplier/add: add a supplier 
                JSON for add: 
                {
                    "name":"Ratan Pvt. Ltd.",
                    "contact":"9998877635",
                    "email":"ratanpvtltd@gmail.com"
                }

            4. /supplier/update/{sid}:update a supplier (same json)
            5. /supplier/remove/{sid}:delete a supplier from db
    3. Auth
    -API END POINTS
            1. /auth/signup: Register a new user
                JSON:
                {
                "name":"Testing",
                "email":"testing@gmail.com",
                "password":"testing"
                }
            2. /auth/authenticate: to authenticate a user
                JSON:
                {
                "email":"testing@gmail.com",
                "password":"testing"
                }

*EXAMPLE CALLS FOR EACH END POINT IS AVAILABLE IN THE FOLDER NAMED "apicalls"*
*DB SCHEMA IS SHARED IN THE FOLDER NAMED "DB Schema" which consists of 3 files, i.e., .txt, .sql and .backup file. all containing the same data*

ALL SERVICES INCLUDING API GATEWAY, CONFIG SERVER AND SERVICE DISCOVERY ARE DONE AND CODED BY © HEMANT ARORA (22CSU270)