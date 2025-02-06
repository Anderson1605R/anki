package br.com.rochadev.anki.controller;


import br.com.rochadev.anki.service.AnkiService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Controller
public class AnkiController {

    @Autowired
    private AnkiService ankiService;

    @GetMapping("/")
    public String index(Model model) {
        // Adiciona um atributo de erro vazio para a primeira carga da página
        model.addAttribute("error", "");
        return "index";
    }

    @PostMapping("/generate-cards")
    public String generateCards(@RequestParam String text, Model model, RedirectAttributes redirectAttributes) {
        // Validação do texto
        if (text == null || text.trim().isEmpty()) {
            model.addAttribute("error", "O campo de texto não pode estar vazio!");
            return "index"; // Retorna para a página inicial com o erro
        }

        // Verificar se contém "Frente:" e "Verso:"
        if (!text.toLowerCase().contains("frente:") || !text.toLowerCase().contains("verso:")) {
            model.addAttribute("error", "O texto deve conter 'Frente:' e 'Verso:' no formato correto.");
            return "index"; // Retorna para a página inicial com o erro
        }

        // Geração do CSV
        String csvContent = ankiService.generateAnkiCards(text);

        // Passa o CSV para a view
        redirectAttributes.addFlashAttribute("csvContent", csvContent);


        redirectAttributes.addFlashAttribute("text", "");


        return "redirect:/download";
    }

    @GetMapping("/download")
    public StreamingResponseBody downloadCsv(Model model, HttpServletResponse response) {
        String csvContent = (String) model.asMap().get("csvContent");

        if (csvContent != null) {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"anki_cards.csv\"");
            return outputStream -> {
                try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                    writer.write(csvContent);
                }
            };
        }

        return null;
    }
}