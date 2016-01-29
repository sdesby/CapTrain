<?php

#http://sylvie-vauthier.developpez.com/tutoriels/php/grand-debutant/?page=formulaires
$ch = curl_init();

$latitude = $_GET['latitude'];
$longitude = $_GET['longitude'];

$url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + $latitude + "&longitude=" + $longitude);

// set URL and other appropriate options
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HEADER, 0);

// grab URL and pass it to the browser
$response = curl_exec($ch);
if(!$response) {
    echo curl_error($ch);
}

// close cURL resource, and free up system resources
curl_close($ch);

print_r($response);
?>
