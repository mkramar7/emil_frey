let EmilFreyRest = function() {};

EmilFreyRest.fetchFromUrl = function(url, successCallback) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            successCallback(data);
        });
};

EmilFreyRest.saveEntity = function(uri, entity, method, successCallback, failureCallback) {
    fetch(uri, {
        method: method,
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(entity),
    }).then(data => {
        if (data.status == 500 || data.status == 400) {
            failureCallback();
        } else {
            successCallback(data);
        }
    })
    .catch(error => {
        console.error("Error:", error);
    });
};

EmilFreyRest.deleteEntity = function(endpointUri, id, successCallback, failureCallback) {
    fetch(endpointUri + "/" + id, {
        method: "DELETE"
    }).then((data) => {
        if (data.status == 500 || data.status == 400) {
            failureCallback();
        } else {
            successCallback();
        }
    }).catch((error) => {
        console.error("Error:", error);
    });
};

EmilFreyRest.deleteMultipleEntities = function(endpointUri, ids, successCallback, failureCallback) {
    fetch(endpointUri, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(ids)
    }).then((data) => {
        if (data.status == 500 || data.status == 400) {
            failureCallback();
        } else {
            successCallback();
        }
    }).catch((error) => {
        console.error("Error:", error);
    });
};