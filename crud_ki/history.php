<?php 
 
 /*
 
 penulis: Muhammad yusuf
 website: https://www.kodingindonesia.com/
 
 */
 
	//Import File Koneksi Database
	require_once('koneksi.php');
	
	//Membuat SQL Query
	$query = "SELECT * FROM tb_komentar";
	
	//Mendapatkan Hasil
	$result = mysqli_query($con,$query);
	
	//Membuat Array Kosong 
    $response = array();

	
	if(mysqli_num_rows($result) > 0){
        $response['success'] = 1;
        $history = array();
        while ($row = mysqli_fetch_assoc($result)){
            # code... 
            array_push($history, $row);
        }
        $response['history'] = $history;
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		// array_push($result,array(
		// "id"=>$row['id'],
        // "title"=>$row['title'],
        // "note"=>$row['note'],
        // "date"=>$row['date']
		// ));
    }
    else {
        $response['success'] = 0;
        $response['message'] = 'no data';
    }
	
	//Menampilkan Array dalam Format JSON
	echo json_encode($response);
	
	mysqli_close($con);


// header("Content-type:application/json");

// require_once('connect.php');

// $query = mysqli_query($conn, "SELECT * FROM 'notes'");

// $response = array();

// while ($row = mysqli_fetch_assoc($query)){
//     array_push($response,
//     array(
//         'id' =>$row['id'],
//         'title' =>$row['title'],
//         'note' =>$row['note'],
//         'date' =>$row['date']
//     ));
// }

// echo json_encode($response);

?>