package br.com.rochadev.anki.service;



import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnkiService {

    public String generateAnkiCards(String text) {
        // Divide o texto em linhas
        String[] lines = text.split("\n");
        List<String[]> cards = new ArrayList<>();
        String question = null;

        // Processa cada linha procurando "Frente" e "Verso"
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("Frente:")) {
                question = line.substring("Frente:".length()).trim();
            } else if (line.startsWith("Verso:") && question != null) {
                String answer = line.substring("Verso:".length()).trim();
                cards.add(new String[]{question, answer});
                question = null; // Reset para o próximo card
            }
        }

        // Converte para CSV
        StringBuilder csv = new StringBuilder();
        for (String[] card : cards) {
            csv.append("\"").append(card[0]).append("\",\"").append(card[1]).append("\"\n");
        }

        return csv.toString();
    }
}
