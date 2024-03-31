<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1>CPS Car Parking System</h1>

<p>This is our client-server project called CPS (Car Parking System), developed in Java. The project revolves around managing a car parking system and incorporates various features to handle parking lot operations efficiently.</p>

<h2>Main Screen</h2>
<img src="https://user-images.githubusercontent.com/103119019/224544264-b8e4398b-6719-486d-ae5d-7db8c9872305.png" alt="Main Screen Image">

<h2>Features</h2>

<ol>
  <li>
    <p>Database Management:</p>
    <ul>
      <li>Virtual parking lot database handling car insertion and removal.</li>
    </ul>
  </li>
  <li>
    <p>Worker Structure:</p>
    <ul>
      <li><b>Parking Lot Worker:</b>
        <ul>
          <li>Reserves parking spaces.</li>
          <li>Adds disabled parking places.</li>
          <li>Refers to alternative parking lots if full.</li>
        </ul>
      </li>
      <li><b>Parking Lot Manager:</b>
        <ul>
          <li>Performs tasks of a worker.</li>
          <li>Responsible for sending price change requests to chain manager.</li>
          <li>Sends reports to chain manager.</li>
        </ul>
      </li>
      <li><b>Customer Service Worker:</b>
        <ul>
          <li>Handles all complaints.</li>
          <li>Responds to complaints with a priority table.</li>
        </ul>
      </li>
      <li><b>Chain Manager:</b>
        <ul>
          <li>Confirms or declines price change requests.</li>
          <li>Views reports sent by managers.</li>
          <li>Monitors parking lot states.</li>
        </ul>
      </li>
    </ul>
  </li>
  <li>
    <p>User Interface:</p>
    <ul>
      <li>Allows users to reserve parking spaces.</li>
      <li>Check-in and check-out functionality.</li>
      <li>Subscription option for better prices.</li>
    </ul>
  </li>
</ol>

<p>This project taught us valuable skills in Java programming, group work dynamics, and handling different personas within a team.</p>

<p>Developed by Mahmoud Hassan and Muhammed Sarahni.</p>

</body>
</html>
