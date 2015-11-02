# Google-Map-Navigation-Add-On
A Google Maps Navigation Add-On will allow users to find a convenient location to stop at while in route to their final destination.

11/1/15 - Loaded the files we have done so far to the repository
Currently, the application is capable of taking three inputs i.e. the source, destination and the stop-off location and placing three markers on each one of them.
We've also incorporated the zoom in and zoom out features. And the current location can also be selected in place of source using GPS co-ordinates.

MainActivity.java:
This is the file from where execution starts i.e. the screen that appears when application launches. An application launcher icon has been added. In the first activity, an image has been added along with two buttons namely - Search and Navigate.

SearchActivity.java:
On clicking the Search button in main activity, the user gets re-directed to this activity. The user can input the place he/she wishes to search. When the user presses the 'Search' button, a marker is placed on the desired location.There are 2 buttons '+' and '-' which will zoom in or zoom out on the marked locations. On clicking the button for current location on the map, the user can view his/her current location on the map. Theres also a Compass on the map, which points northward, even if the user rotates the map.

MapsActivity.java:
On clicking the Navigate button in main activity, the user gets re-directed to this activity. The user needs to enter 3 inputs namely - source, destination and stop-off location. A button for 'current location' is added. On clicking the button, the GPS coordinates of the current location are considered as the source. There are 2 buttons '+' and '-' which will zoom in or zoom out on the marked locations. The same functionality of the map in the SearchActivity is implemented here as well.

AndroidManifest.xml:
This file contains all the permissions listed as below:
INTERNET, ACCESS_NETWORK_STATE, WRITE_EXTERNAL_STORAGE,READ_GSERVICES, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION

We've also used Google Maps API Key for Android Version

