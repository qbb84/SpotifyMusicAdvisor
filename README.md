

# Music Advisor

Music Advisor is a Java application that leverages the Spotify API to provide information about new releases, categories, playlists, and featured tracks. Users can authenticate with their Spotify credentials to access personalized recommendations.

## Getting Started

Follow the steps below to set up and run the Music Advisor:

1. Clone the repository:

   ```bash
   git clone https://github.com/rewindbytes/SpotifyMusicAdvisor
   cd SpotifyMusicAdvisor
   ```

2. Open the project in your preferred Java development environment.

3. Obtain your Spotify API credentials:
   - Go to the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/applications).
   - Create a new application to get your Client ID and Client Secret.
   
4. Update the APIAuthorization class with your credentials:
   ```java
   package advisor.API;

   public class APIAuthorization {
       public static String CLIENT_ID = "your-client-id";
       public static String CLIENT_SECRET = "your-client-secret";
   }
   ```

5. Build and run the project.

## Usage

1. Run the application and enter your Spotify Client ID and Client Secret when prompted.

2. Follow the provided link to authenticate and obtain the authorization code.

3. After successful authentication, you can use the following commands:
   - `new`: Get information about new releases.
   - `categories`: Get a list of available music categories.
   - `playlist <name>`: Get a playlist by name.
   - `featured`: Get featured playlists.

## Example

```bash
Enter your Spotify Client ID: your-client-id
Enter your Spotify Client Secret: your-client-secret

Authentication:
1. Run the 'auth' command, follow the link and authorize the application.
2. If successful, you will be notified and will be able to access the Spotify API.


Current Available commands:
1. new
2. categories
3. playlist <name>
4. featured
5. exit

Enter your choice: E.G 'featured'
...
```

## License

This project is licensed under the [MIT License](LICENSE).
