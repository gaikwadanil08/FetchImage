<?php 
 
 include 'dbconfig.php';
 
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 //creating a query
 $stmt = $conn->prepare("SELECT id, image_title, image_url FROM images;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $image_title, $image_url);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['image_title'] = $image_title; 
 $temp['image_url'] = $image_url; 
 
 array_push($images, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($images);