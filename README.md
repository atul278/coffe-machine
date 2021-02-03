# coffe-machine

1.  Start this Application
    -  `mvn clean install`
    -  `mvn sping-boot:run`

2.  Entrypoint of application to test using API in postman
    -   `localhost:8080/availability`
    -   Requsest Type : POST
    -   Body : pick `dataDefult.json` or `dataLarge.json`
    -   Note-1 : change the `count_n` in body to test change in simultaneously pouring of Beverages
    -   Note-2 : Change quantity in `total_items_quantity` to see the change in impact on beverage puring according to change in available quantity
    
3.  To test the same using the test use `mvn test` by altering data in `src/test/resources/dataLarge.json` and `src/test/resources/dataDefault.json`

4. Thing not taken in consideration is refilling of ingredient and there status indicator based on assumption on machine working with per request without any persistent database
