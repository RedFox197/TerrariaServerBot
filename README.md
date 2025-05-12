# Terraria Bot - OUTDATED (2022)

Questo è un bot che avevo fatto circa nel 2022 per collegare la mia chat Twitch al server Terraria. È un progetto vecchio, quindi potrebbe non funzionare al 100% e non è fatto per essere usato da altri senza fare modifiche al codice.

L'ho sviluppato facendo reverse engineering con Wireshark e .dotPeek di JetBrains per analizzare il protocollo di rete di Terraria, ma non significa che i vari Packet siano fedeli, il mio obiettivo era semplicemente riuscire a far entrare il bot nel server e fargli mandare messaggi in chat, per tanto, se la memoria non mi inganna, alcuni Packet hanno cose in meno perché non mi servivano ecc.

Anche il client Twitch è vecchio. Avevo convertito la libreria ufficiale da JavaScript a Java, non so se sia ancora valida. Attualmente Twitch sembra stia per togliere anche direttamente la chat IRC.

Ho caricato il progetto, principalmente come backup, e magari un giorno lo aggiornerò.
Per ora mi limito a commentare la parte di Twitch e cambio il protocollo di Terraria in 279 che ho visto faccia riferimento alla versione attuale Terraria 1.4.4.9. 

Comunque sembra che il bot riesca a connettersi e inviare messaggi senza problemi avendo aggiornato solo la versione del protocollo.
