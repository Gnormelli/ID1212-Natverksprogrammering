package laboration_1;
/**
Q: Vad betyder TCP-headers i datagrammen?
        A: Transmission Control Protocol (TCP) är ett huvud protokoll för kommunikation på internet. Dvs kominications protocol som definerar hur system kommunicerar med varandra på transport lagret. Flaggorna nedan indicerar vilken fas etablerigen av kommunikation är i eller om någon mer information ska skickas med.

        Q: Vad betyder flaggorna ACK/SYN/SEQ/PSH/FIN?
        A:
        SYN (synchronize): Paket som är till för att starta uppkopplingar och se till att systemen är synkroniserade. Kallas ofta handskakningsförfarande.
        ACK (acknowledgment): Paket som konfirmerar att paket har blivit mottagna samt konfirmerar start förfrågningar.
        FIN (finish): Används för att säga “vi är klara med varandra, vi stänger ner uppkopplingen”, båda deltagarna skickar en sådan om möjligt.
        PSH (push): Säger “denna data ska bli skickas vidare direkt inte bli buffrad”
        SEQ: Visar vilket paket som system ska förvänta sig; ex #2 eller #3, om vi får seq = #2 på ett packet och paketet efter är #7 så vet vi att vi tappade några paket emellan.
*/