<?php
 
 /*
 
 penulis: Muhammad yusuf
 website: http://www.kodingindonesia.com/
 
 */
 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$no_rm = $_POST['no_rm'];
		$kritik = $_POST['kritik'];
		$saran = $_POST['saran'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_komentar (no_rm,kritik,saran) VALUES ('$no_rm','$kritik','$saran')";
		
		//Import File Koneksi database
		require_once('koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Komentar';
		}else{
			echo 'Gagal Menambahkan Komentar';
		}
		
		mysqli_close($con);
	}
?>