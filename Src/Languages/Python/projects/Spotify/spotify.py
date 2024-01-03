# To access Spotipy
import spotipy
# To View the API response
import json
# To open our song in our default browser
import webbrowser

def search_Song(song):
    username = '12166559717'
    clientID = '7186ac6407f64a74866f14e9c5bb5ac8'
    clientSecret = '28b29062ab134e2bad1e92803e8173e4'
    redirectURI = 'http://google.com/'
    
    # Create OAuth Object
    oauth_object = spotipy.SpotifyOAuth(clientID,clientSecret,redirectURI)

    # Create token
    token_dict = oauth_object.get_access_token()
    token = token_dict['access_token']

    # Create Spotify Object
    spotifyObject = spotipy.Spotify(auth=token)

    user = spotifyObject.current_user()
    # To print the response in readable format.
    print(json.dumps(user,sort_keys=True, indent=4))

    
    while on:
            # Search for the Song.
            searchResults = spotifyObject.search("Mercy",1,0,"track")
            # Get required data from JSON response.
            tracks_dict = searchResults['tracks']
            tracks_items = tracks_dict['items']
            song = tracks_items[0]['external_urls']['spotify']
            # Open the Song in Web Browser
            webbrowser.open(song)
            on = False
            
