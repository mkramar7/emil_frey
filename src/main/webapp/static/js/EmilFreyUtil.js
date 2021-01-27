let EmilFreyUtil = function() {};

EmilFreyUtil.loadLeadsTableWithData = function() {
    let tableBody = EmilFreyUtil.clearAndGetTableBody("leads-table");

    EmilFreyRest.fetchFromUrl("api/leads", function(leads) {
        leads.forEach((lead, i) => {
            let rowElem = EmilFreyUtil.createRow(tableBody);
            EmilFreyUtil.createCheckboxCell(i+1, rowElem);
            EmilFreyUtil.createRowNumCell(i+1, rowElem);
            EmilFreyUtil.createDataCell(lead.id, rowElem, true);
            EmilFreyUtil.createDataCell(lead.firstName, rowElem);
            EmilFreyUtil.createDataCell(lead.lastName, rowElem);
            EmilFreyUtil.createActionsCell(rowElem, lead.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

EmilFreyUtil.loadCarCategoriesTableWithData = function() {
    let tableBody = EmilFreyUtil.clearAndGetTableBody("car-categories-table");

    EmilFreyRest.fetchFromUrl("api/car_categories", function(carCategories) {
        carCategories.forEach((carCategory, i) => {
            let rowElem = EmilFreyUtil.createRow(tableBody);
            EmilFreyUtil.createCheckboxCell(i+1, rowElem);
            EmilFreyUtil.createRowNumCell(i+1, rowElem);
            EmilFreyUtil.createDataCell(carCategory.id, rowElem, true);
            EmilFreyUtil.createDataCell(carCategory.categoryName);
            EmilFreyUtil.createActionsCell(rowElem, carCategory.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

EmilFreyUtil.loadCarsTableWithData = function() {
    let tableBody = EmilFreyUtil.clearAndGetTableBody("cars-table");

    EmilFreyRest.fetchFromUrl("api/cars", function(cars) {
        cars.forEach((car, i) => {
            let rowElem = EmilFreyUtil.createRow(tableBody);
            EmilFreyUtil.createCheckboxCell(i+1, rowElem);
            EmilFreyUtil.createRowNumCell(i+1, rowElem);
            EmilFreyUtil.createDataCell(car.id, rowElem, true);
            EmilFreyUtil.createDataCell(car.code, rowElem);
            EmilFreyUtil.createDataCell(car.name, rowElem);
            EmilFreyUtil.createActionsCell(rowElem, car.id);
        });

        $("[data-toggle='tooltip']").tooltip();
    });
};

EmilFreyUtil.createCheckboxCell = function (index, parentElem){
    let checkboxCell = $(document.createElement("td")).appendTo(parentElem);
    checkboxCell.addClass("checkbox-col");

    let customControlElem = $(document.createElement("div")).appendTo(checkboxCell);
    customControlElem.addClass("custom-control custom-checkbox");

    let inputCheckbox = $(document.createElement("input")).appendTo(customControlElem);
    inputCheckbox.attr("type", "checkbox");
    inputCheckbox.addClass("form-check-input");
    inputCheckbox.data("for-row", index);
}

EmilFreyUtil.createRowNumCell = function(index, parentElem) {
    let rowNumCell = $(document.createElement("th")).appendTo(parentElem);
    rowNumCell.attr("scope", "row");
    rowNumCell.text(index);
};

EmilFreyUtil.createDataCell = function(value, parentElem, hidden) {
    let idCell = $(document.createElement("td")).appendTo(parentElem);
    idCell.text(value);
    if (hidden) {
        idCell.css("display", "none");
    }
    return idCell;
};

EmilFreyUtil.createActionsCell = function(parentElem, id) {
    let actionsCell = $(document.createElement("td")).appendTo(parentElem);
    actionsCell.addClass("actions-cell");
    actionsCell.attr("align", "center");

    let editActionIcon = $(document.createElement("i")).appendTo(actionsCell);
    editActionIcon.addClass("edit-action text-warning fas fa-pen");
    editActionIcon.data("id-to-edit", id);
    editActionIcon.attr({
        "title" : "Edit",
        "data-toggle" : "tooltip",
        "data-placement" : "top"
    });

    actionsCell.append("&nbsp;&nbsp;&nbsp;");

    let deleteActionIcon = $(document.createElement("i")).appendTo(actionsCell);
    deleteActionIcon.addClass("delete-action text-danger fas fa-trash");
    deleteActionIcon.data("id-to-delete", id);
    deleteActionIcon.attr({
        "title" : "Delete",
        "data-toggle" : "tooltip",
        "data-placement" : "top"
    });
};

EmilFreyUtil.clearAndGetTableBody = function(tableId) {
    let tableBody = $("#" + tableId + " tbody");
    tableBody.html("");
    return tableBody;
};

EmilFreyUtil.createRow = function(tableBody) {
    return $(document.createElement("tr")).appendTo(tableBody);
};

EmilFreyUtil.showModalDialog = function(dialogId, entityId, fieldId) {
    $("#" + dialogId).modal("show");
    if (entityId && fieldId) {
        $("#" + fieldId).val(entityId);
    }
};

// onDialogShown events
EmilFreyUtil.onLeadDialogShown = function() {
    var selectedLead = null;
    if ($("#edit-lead-id").val() !== "") {
        EmilFreyRest.fetchFromUrl("api/leads/" + $("#edit-lead-id").val(), function(lead) {
            selectedLead = lead;
            fillLeadEditForm();
            EmilFreyUtil.checkFormValidity($("#lead-dialog"));
        });
    } else {
        fillLeadEditForm();
        EmilFreyUtil.checkFormValidity($("#lead-dialog"));
    }

    function fillLeadEditForm() {
        if (selectedLead) {
            $("#edit-lead-text").val(selectedLead.lead);
        }

        if (!selectedLead) {
            $("#lead-dialog .answer-input-group").each(function(index) {
                let i = index + 1;
                $(this).data("for-answer-id", i);
                if (i == 1) {
                    $(this).find(".edit-lead-answer-radio").prop("checked", true);
                }
            });
        } else {
            let i=0;
            for (let answerId in selectedLead.answers) {
                let answer = selectedLead.answers[answerId];
                let answerInputGroup = $("#lead-dialog .answer-input-group:eq('" + i + "')");
                answerInputGroup.find(".edit-lead-answer-text").val(answer);
                answerInputGroup.data("for-answer-id", answerId);
                if (parseInt(answerId) === selectedLead.correctAnswerId) {
                    answerInputGroup.find(".edit-lead-answer-radio").prop("checked", true);
                }
                i++;
            }
        }

        $("#edit-lead-text").focus();
    }
};

EmilFreyUtil.onCarCategoryDialogShown = function() {
    var selectedCarCategory = null;
    if ($("#edit-car-category-id").val() !== "") {
        EmilFreyRest.fetchFromUrl("api/car_categories/" + $("#edit-car-category-id").val(), function(carCategory) {
            selectedCarCategory = carCategory;
            fillCarCategoryEditForm();
            EmilFreyUtil.checkFormValidity($("#car-category-dialog"));
        });
    } else {
        fillCarCategoryEditForm();
        EmilFreyUtil.checkFormValidity($("#car-category-dialog"));
    }

    function fillCarCategoryEditForm() {
        if (selectedCarCategory) {
            $("#edit-car-category-name").val(selectedCarCategory.name);
        }

        $("#edit-car-category-name").focus();
    }
};

EmilFreyUtil.onCarDialogShown = function() {
    var selectedCar = null;
    if ($("#edit-car-id").val() !== "") {
        EmilFreyRest.fetchFromUrl("api/cars/" + $("#edit-car-id").val(), function(car) {
            selectedCar = car;
            fillCarEditForm();
            EmilFreyUtil.checkFormValidity($("#car-dialog"));
        });
    } else {
        fillCarEditForm();
        EmilFreyUtil.checkFormValidity($("#car-dialog"));
    }

    function fillCarEditForm() {
        if (selectedCar) {
            $("#edit-car-manufacturer").val(selectedCar.manufacturer);
            $("#edit-car-model").val(selectedCar.model);
            $("#edit-car-manufacturing-date").val(selectedCar.manufacturingDate);
        }

        $("#edit-car-manufacturer").focus();
    }
};

// onDialogHidden events
EmilFreyUtil.onLeadDialogHidden = function() {
    EmilFreyUtil.removeFormValidationErrors($("#lead-dialog"));

    $("#edit-lead-id").val("");
    $("#edit-lead-first-name").val("");
    $("#edit-lead-last-name").html("");
};

EmilFreyUtil.onCarCategoryDialogHidden = function() {
    EmilFreyUtil.removeFormValidationErrors($("#car-category-dialog"));

    $("#edit-car-category-id").val("");
    $("#edit-car-category-name").val("");
};

EmilFreyUtil.onCarDialogHidden = function() {
    EmilFreyUtil.removeFormValidationErrors($("#car-dialog"));

    $("#edit-car-id").val("");
    $("#edit-car-manufacturer").val("");
    $("#edit-car-model").val("");
    $("#edit-car-manufacturing-date").val("");
};

// Save button click events
EmilFreyUtil.saveLead = function() {
    if (!EmilFreyUtil.checkFormValidity($("#lead-dialog"))) {
        return;
    }

    let lead = {};
    lead.firstName = $("#edit-lead-first-name").val();
    lead.lastName = $("#edit-lead-last-name").val();

    let leadId = $("#edit-lead-id").val();
    let uri = "api/leads" + (leadId ? "/" + leadId : "");
    let method = leadId ? "PUT" : "POST";
    EmilFreyRest.saveEntity(uri, lead, method, function(data) {
        $("#lead-dialog").modal("hide");
        EmilFreyUtil.loadLeadsTableWithData();
        EmilFreyUtil.showSuccessMessage("Lead saved successfully!");
    });
};

EmilFreyUtil.saveCarCategory = function() {
    if (!EmilFreyUtil.checkFormValidity($("#car-category-dialog"))) {
        return;
    }

    let carCategory = {};
    carCategory.categoryName = $("#edit-car-category-name").val();

    let carCategoryId = $("#edit-car-category-id").val();
    let uri = "api/car_categories" + (carCategoryId ? "/" + carCategoryId : "");
    let method = carCategoryId ? "PUT" : "POST";
    EmilFreyRest.saveEntity(uri, carCategory, method, function(data) {
        $("#car-category-dialog").modal("hide");
        EmilFreyUtil.loadCarCategoriesTableWithData();
        EmilFreyUtil.showSuccessMessage("Car category saved successfully!");
    });
};

EmilFreyUtil.saveCar = function() {
    if (!EmilFreyUtil.checkFormValidity($("#car-dialog"))) {
        return;
    }

    let car = {};
    car.manufacturer = $("#edit-car-manufacturer").val();
    car.model = $("#edit-car-model").val();
    car.manufacturingDate = $("#edit-car-manufacturing-date").val();

    let carId = $("#edit-car-id").val();
    let uri = "api/cars" + (carId ? "/" + carId : "");
    let method = carId ? "PUT" : "POST";
    EmilFreyRest.saveEntity(uri, car, method, function(data) {
        $("#car-dialog").modal("hide");
        EmilFreyUtil.loadCarsTableWithData();
        EmilFreyUtil.showSuccessMessage("Car saved successfully!");
    });
};

EmilFreyUtil.showSuccessMessage = function(message) {
    EmilFreyUtil.showMessage(message, "success");
};

EmilFreyUtil.showErrorMessage = function(message) {
    EmilFreyUtil.showMessage(message, "error");
};

EmilFreyUtil.showMessage = function(message, prefix) {
    let alertMessage = $(document.createElement("div")).appendTo($("body"));
    alertMessage.addClass("alert alert-" + prefix + " " + prefix + "-message");
    alertMessage.attr("role", "alert");
    alertMessage.html(message);
    alertMessage.fadeIn("slow");
    setTimeout(function() {
        alertMessage.fadeOut("slow", function() {
            $(this).remove();
        });
    }, 5000);
};

EmilFreyUtil.checkFormValidity = function(modalDialog) {
    let form = modalDialog.find("form");
    if (form.get(0).checkValidity() === false) {
        form.addClass("was-validated");
        return false;
    } else {
        form.removeClass("was-validated");
        return true;
    }
};

EmilFreyUtil.removeFormValidationErrors = function(modalDialog) {
    modalDialog.find("form").removeClass("was-validated");
};