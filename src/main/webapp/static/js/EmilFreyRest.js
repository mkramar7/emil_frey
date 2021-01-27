let EmilFreyRest = function() {};

EmilFreyRest.fetchFromUrl = function(url, successCallback) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            successCallback(data);
        });
};

EmilFreyRest.saveEntity = function(uri, entity, method, successCallback) {
    fetch(uri, {
        method: method,
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(entity),
    }).then((data) => {
            successCallback(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });
};

EmilFreyRest.deleteEntity = function(endpointUri, id, successCallback) {
    fetch(endpointUri + "/" + id, {
        method: "DELETE"
    }).then(() => {
        successCallback();
    }).catch((error) => {
        console.error("Error:", error);
    });
};