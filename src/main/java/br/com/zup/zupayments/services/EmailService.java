package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.PedidoDeCompra;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final SpringTemplateEngine springTemplateEngine;
    private final JavaMailSender javaMailSender;

    public EmailService(SpringTemplateEngine springTemplateEngine, JavaMailSender javaMailSender) {
        this.springTemplateEngine = springTemplateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmailDePedidoPendenteDeNotaFiscal(PedidoDeCompra pedidoDeCompra) throws MessagingException {
        MimeMessage mensagem = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(pedidoDeCompra.getResponsavel().getEmail());
        helper.setSubject("URGENTE: Nota fiscal pendente - pedido " + pedidoDeCompra.getNumeroDePedido());
        helper.setFrom("staff@zup.com.br");

        Context context = new Context();
        context.setVariable("nomeResponsavel", pedidoDeCompra.getResponsavel().getNomeCompleto());
        context.setVariable("numeroDoPedido", pedidoDeCompra.getNumeroDePedido());
        context.setVariable("cnpjOucpf", pedidoDeCompra.getFornecedor().getCnpjOuCpf());
        context.setVariable("razaoSocial", pedidoDeCompra.getFornecedor().getRazaoSocial());

        String emailHtml = springTemplateEngine.process("mail", context);
        helper.setText(emailHtml, true);

        javaMailSender.send(mensagem);
    }
}
