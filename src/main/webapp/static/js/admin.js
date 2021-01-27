let allHeadCheckboxes = $("table thead .checkbox-col input[type='checkbox']");

$(function() {
    EmilFreyUtil.loadLeadsTableWithData();
    handleTabSwitchingBehavior();
    handleCheckboxesBehavior();
    handleNewEntryButtonClicks();
    handleEditEntryActionIconClicks();
    handleDialogShownAndHiddenEvents();
    handleDialogSaveEvents();
    handleDeleteEntryActionIconClicks();
});

function handleDialogSaveEvents() {
    $("#lead-dialog #btn-save-lead").click(EmilFreyUtil.saveLead);
    $("#car-category-dialog #btn-save-car-category").click(EmilFreyUtil.saveCarCategory);
    $("#car-dialog #btn-save-car").click(EmilFreyUtil.saveCar);
};

function handleDialogShownAndHiddenEvents() {
    $("#lead-dialog").on("shown.bs.modal", EmilFreyUtil.onLeadDialogShown);
    $("#car-category-dialog").on("shown.bs.modal", EmilFreyUtil.onCarCategoryDialogShown);
    $("#car-dialog").on("shown.bs.modal", EmilFreyUtil.onCarDialogShown);

    $("#lead-dialog").on("hidden.bs.modal", EmilFreyUtil.onLeadDialogHidden);
    $("#car-category-dialog").on("hidden.bs.modal", EmilFreyUtil.onCarCategoryDialogHidden);
    $("#car-dialog").on("hidden.bs.modal", EmilFreyUtil.onCarDialogHidden);
};

function handleNewEntryButtonClicks() {
    $(".btn-add-new-entry").click(function() {
        if ($(this).hasClass("btn-add-new-lead")) {
            EmilFreyUtil.showModalDialog("lead-dialog");
        } else if ($(this).hasClass("btn-add-new-car-category")) {
            EmilFreyUtil.showModalDialog("car-category-dialog");
        } else if ($(this).hasClass("btn-add-new-car")) {
            EmilFreyUtil.showModalDialog("car-dialog");
        }
    });
};

function handleEditEntryActionIconClicks() {
    $("#leads-table").on("click", ".edit-action", function() {
        EmilFreyUtil.showModalDialog("lead-dialog", $(this).data("id-to-edit"), "edit-lead-id");
    });

    $("#car-categories-table").on("click", ".edit-action", function() {
        EmilFreyUtil.showModalDialog("car-category-dialog", $(this).data("id-to-edit"), "edit-car-category-id");
    });

    $("#cars-table").on("click", ".edit-action", function() {
        EmilFreyUtil.showModalDialog("car-dialog", $(this).data("id-to-edit"), "edit-car-id");
    });
};

function handleDeleteEntryActionIconClicks() {
    $("#leads-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected lead?")) {
           EmilFreyRest.deleteEntity("leads", idToDelete, function() {
               EmilFreyUtil.loadLeadsTableWithData();
               EmilFreyUtil.showSuccessMessage("Lead deleted successfully!");
           });
        }
    });

    $("#car-categories-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected car category?")) {
            EmilFreyRest.deleteEntity("car_categories", idToDelete, function() {
                EmilFreyUtil.loadCarCategoriesTableWithData();
                EmilFreyUtil.showSuccessMessage("Car category deleted successfully!");
            });
        }
    });

    $("#cars-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected car?")) {
            EmilFreyRest.deleteEntity("cars", idToDelete, function() {
                EmilFreyUtil.loadCarsTableWithData();
                EmilFreyUtil.showSuccessMessage("Car deleted successfully!");
            });
        }
    });
};

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        allHeadCheckboxes.prop("checked", false);

        let tab = $(e.target).data("tab");
        if (tab === "leads") {
            EmilFreyUtil.loadLeadsTableWithData();
        } else if (tab === "car-categories") {
            EmilFreyUtil.loadCarCategoriesTableWithData();
        } else if (tab === "cars") {
            EmilFreyUtil.loadCarsTableWithData();
        }
    })
};

function handleCheckboxesBehavior() {
    allHeadCheckboxes.click(function() {
        let parentTable = $(this).closest("table");
        let allBodyCheckboxes = parentTable.find("tbody .checkbox-col input[type='checkbox']");
        allBodyCheckboxes.prop("checked", $(this).is(":checked"));
    });
};