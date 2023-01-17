# javaForTesters
project contains 3 parts: 
1) Code base for testing
2) Tests
3) Xampp app 
All parts were added to the docker compose file. 
For Xampp compose part need to use "addressbook.7z".
    volumes:
      - {path tp the unziped addressbook.7z}:/www
      By default, addressbook folder has Xampp location: \\xampp\htdocs\addressbook
