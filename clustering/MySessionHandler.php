<?php
class MySessionHandler extends SessionHandler
{
    private $db;

    public function __construct($db)
    {
        $this->db = $db;
    }

    public function open($savePath, $sessionName)
    {
        // Cette méthode est appelée lors de l'ouverture de la session
        // Vous pouvez initialiser la connexion à la base de données ici
        return true;
    }

    public function close()
    {
        // Cette méthode est appelée lors de la fermeture de la session
        // Vous pouvez fermer la connexion à la base de données ici
        return true;
    }

    public function read($sessionId)
    {
        // Cette méthode est appelée pour lire les données de la session depuis la base de données
        $result = $this->db->query("SELECT data FROM sessions WHERE id = '$sessionId'");
        $row = $result->fetch_assoc();
        return $row ? $row['data'] : '';
    }

    public function write($sessionId, $data)
    {
        // Cette méthode est appelée pour écrire les données de la session dans la base de données
        $data = $this->db->real_escape_string($data);
        $this->db->query("INSERT INTO sessions (id, data) VALUES ('$sessionId', '$data')");
        return true;
    }

    public function destroy($sessionId)
    {
        // Cette méthode est appelée lorsqu'une session est détruite
        $this->db->query("DELETE FROM sessions WHERE id = '$sessionId'");
        return true;
    }

    public function gc($maxLifetime)
    {
        // Cette méthode est appelée pour effectuer la collecte des sessions expirées
        // $timestamp = time() - $maxLifetime;
        // $this->db->query("DELETE FROM sessions WHERE timestamp < $timestamp");
        return true;
    }
}