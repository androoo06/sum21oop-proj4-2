# HELP SECTION

This help section is dedicated to Rey. lol

## Startup

Upon startup, you will have 2 options: create new list, & open existing list
Both buttons will take you to the list screen, but the open existing list button allows you to choose a text file from which you would like to use data.

## List Screen

When your screen switches to the list controller screen, you will notice a tableview with 4 columns as well as a horizontal toolbar at the top of the window.
Below is every usage in the list screen

### Filter box
The first choicebox/dropdown is the filter box, where you can select how you'd like to filter the items currently in the tableview.

Filter options are:
- No filter
- Complete
- Incomplete
- Selected

if your list is applicable to change, you will notice immediate visual changes to your table's display

### Save to file
The second left-most item is a button titled "Save to file" which will prompt a filechooser to select a file to save to. The application WILL OVERWRITE any data previously in the file when saving.

### Add new item
The third left-most item is a button titled "Add new item" which will insert a "default" item into your tableview. Every item in the tableview is editable: see Edit Items below

### Delete Selected
The second to last item is a button titled "Delete Selected" which will delete every item in your table that currently has the selected box checked. This cannot be undone.

### Return to Menu
The last item is a button titled "Return to menu" which, upon clicking, will create a big popup window confirming whether or not you'd like to go back to the main screen. This is done to ensure no accidents happen, as the Application does not save data unless explicitly told to do so.

### Edit Items
Click or doubleclick on a property of an item (description or duedate) to begin editing that item. Hit enter once finished to "commit edits".

## Extra
There is also a label at the bottom that warns users that closing the Application (hitting the X) will not save data, to ensure users appropriately save their data (if wanted) before closing the application.
There is a button in the selected TableColumn that is marked "T" for toggle. Hit this to select/deselect all of the current list's items.