A spring boot base mircroservice that exposes api for fething a mashup message containing artist and album information

Enpoint for requesting:

    http://<server ip>:3200/mashup/{mbid}

mbid is a uuid identifier. https://musicbrainz.org/doc/MusicBrainz_Identifier 

Find some mbIds here to test with by clicking artist link the details, https://musicbrainz.org/doc/Artist.
examples:

    Snoop-dog - f90e8b26-9e52-4669-a5c9-e28529c47894,
    Nirvana - 5b11f4ce-a62d-471e-81fc-a69a8278c7da
    Coldplay - cc197bad-dc9c-440d-a5b5-d52ba2e14234

There is a rateLimiter 1 request per second. musicBrainz have the same request limit 1req/s per user.
However underlying api requests escpically for coverArtArchive is quite slow. A cache would probably improve performance significantly.
Basic auth is enabled for the api. 

    username/password = admin 

Example output:

    {
        "mbId": "f90e8b26-9e52-4669-a5c9-e28529c47894",
        "description": "<p class=\"mw-empty-elt\">\n\n</p>\n<p><b>Calvin Cordozar Broadus Jr.</b> (born October 20, 1971), known professionally as...",
        "albums": [
            {
                "title": "Doggystyle",
                "id": "649762c9-8785-3d9c-803d-2f0496f168e5",
                "imageUrl": "http://coverartarchive.org/release/7e422cee-608c-4f5a-9740-729f012e121b/6733051227.jpg"
            },
            ....
        ]
    }

Underlying apies:

    https://musicbrainz.org/doc/MusicBrainz_API
    https://www.wikidata.org/w/api.php
    https://www.mediawiki.org/wiki/API:Main_page,
    https://wiki.musicbrainz.org/Cover_Art_Archive/API

Future releases and improvements:

    -Add a cache solution.
    -Add proper errorHandling.
    -Add unittests and integration/blackbox tests.
    -Add swagger docs.
    -Improve security, use oauth 2.0 instead of basic auth.
    -Improve Maven build with autoformatting and run test suite on clean install