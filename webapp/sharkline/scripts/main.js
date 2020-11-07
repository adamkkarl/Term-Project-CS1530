function suggestion(){
    let bodyElement = document.body;
    let suggestionsList = document.createElement('div');
    let itemList = document.createElement('div');
    
    suggestionsList.className = "suggestions";
    itemList.id = "items-list";
    itemList.className = "row row-cols-1 row-cols-md-3";
    itemList.setAttribute("padding-left", "15px")

    bodyElement.append(suggestionsList);
    suggestionsList.append(itemList);

    var numOfUsers = 9; //change to size of array
    var i;
    for (i = 0; i < numOfUsers; i++) {
        var name = "John Doe"; //change to get name of user i
        var path = "https://icon-library.com/images/free-profile-icon/free-profile-icon-25.jpg"; //change to path of image
        let cardElement = document.createElement('div');
        let imageElement = document.createElement('img');
        let infoContainer = document.createElement('div');
        let headingElement = document.createElement('h5');
        let btnElement = document.createElement('a');

        cardElement.className = "card center";
        imageElement.className = "image";
        infoContainer.className = "card-body";
        headingElement.className = "card-title";
        btnElement.className = "btn btn-primary";

        imageElement.src = path;
        btnElement.setAttribute("href", "#");
        imageElement.setAttribute("alt", "https://icon-library.com/images/free-profile-icon/free-profile-icon-25.jpg");
        headingElement.innerText = name;
        btnElement.innerText = "Connect";

        itemList.appendChild(cardElement);
        cardElement.append(imageElement, infoContainer);
        infoContainer.append(headingElement, btnElement);
    }
}