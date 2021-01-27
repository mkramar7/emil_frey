let allHeadCheckboxes = $("table thead .checkbox-col input[type='checkbox']");

$(function() {
    /*QuizillaUtil.loadQuestionsTableWithData();
    handleTabSwitchingBehavior();
    handleCheckboxesBehavior();
    handleNewEntryButtonClicks();
    handleEditEntryActionIconClicks();
    handleDialogShownAndHiddenEvents();
    handleDialogSaveEvents();
    handleDeleteEntryActionIconClicks();
    */
});

function handleDialogSaveEvents() {
    $("#question-dialog #btn-save-question").click(QuizillaUtil.saveQuestion);
    $("#category-dialog #btn-save-category").click(QuizillaUtil.saveCategory);
    $("#language-dialog #btn-save-language").click(QuizillaUtil.saveLanguage);
};

function handleDialogShownAndHiddenEvents() {
    $("#question-dialog").on("shown.bs.modal", QuizillaUtil.onQuestionDialogShown);
    $("#category-dialog").on("shown.bs.modal", QuizillaUtil.onCategoryDialogShown);
    $("#language-dialog").on("shown.bs.modal", QuizillaUtil.onLanguageDialogShown);

    $("#question-dialog").on("hidden.bs.modal", QuizillaUtil.onQuestionDialogHidden);
    $("#category-dialog").on("hidden.bs.modal", QuizillaUtil.onCategoryDialogHidden);
    $("#language-dialog").on("hidden.bs.modal", QuizillaUtil.onLanguageDialogHidden);
};

function handleNewEntryButtonClicks() {
    $(".btn-add-new-entry").click(function() {
        if ($(this).hasClass("btn-add-new-question")) {
            QuizillaUtil.showModalDialog("question-dialog");
        } else if ($(this).hasClass("btn-add-new-category")) {
            QuizillaUtil.showModalDialog("category-dialog");
        } else if ($(this).hasClass("btn-add-new-language")) {
            QuizillaUtil.showModalDialog("language-dialog");
        }
    });
};

function handleEditEntryActionIconClicks() {
    $("#questions-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("question-dialog", $(this).data("id-to-edit"), "edit-question-id");
    });

    $("#categories-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("category-dialog", $(this).data("id-to-edit"), "edit-category-id");
    });

    $("#languages-table").on("click", ".edit-action", function() {
        QuizillaUtil.showModalDialog("language-dialog", $(this).data("id-to-edit"), "edit-language-id");
    });
};

function handleDeleteEntryActionIconClicks() {
    $("#questions-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected question?")) {
           QuizillaRest.deleteEntity("api/questions", idToDelete, function() {
               QuizillaUtil.loadQuestionsTableWithData();
               QuizillaUtil.showSuccessMessage("Question deleted successfully!");
           });
        }
    });

    $("#categories-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected question?")) {
            QuizillaRest.deleteEntity("api/categories", idToDelete, function() {
                QuizillaUtil.loadCategoriesTableWithData();
                QuizillaUtil.showSuccessMessage("Category deleted successfully!");
            });
        }
    });

    $("#languages-table").on("click", ".delete-action", function() {
        let idToDelete = $(this).data("id-to-delete");
        if (confirm("Are you sure that you want to delete selected question?")) {
            QuizillaRest.deleteEntity("api/languages", idToDelete, function() {
                QuizillaUtil.loadLanguagesTableWithData();
                QuizillaUtil.showSuccessMessage("Language deleted successfully!");
            });
        }
    });
};

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        allHeadCheckboxes.prop("checked", false);

        let tab = $(e.target).data("tab");
        if (tab === "questions") {
            QuizillaUtil.loadQuestionsTableWithData();
        } else if (tab === "categories") {
            QuizillaUtil.loadCategoriesTableWithData();
        } else if (tab === "languages") {
            QuizillaUtil.loadLanguagesTableWithData();
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