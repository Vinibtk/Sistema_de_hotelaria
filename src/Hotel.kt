class Quarto(
    val numero: Int,
    val tipo: String,
    val valorDiaria: Double
) {
    fun vantagensExtras(): String = when (tipo.lowercase()) {
        "standard"     -> "Café da manhã incluso"
        "luxo"         -> "Café da manhã + Frigobar + Vista para o mar"
        "presidencial" -> "Café da manhã + Frigobar + Vista para o mar + Jacuzzi + Motorista"
        else           -> "Sem vantagens adicionais cadastradas"
    }
}


class Hospede(
    val nome: String,
    val cpf: String,
    val pedidoEspecial: String? = null
)

class Reserva(
    val hospede: Hospede,
    val quarto: Quarto,
    val quantidadeDias: Int,
    val checkInAntecipado: Boolean = false,
    val despesasExtras: List<Double> = emptyList()
) {

    companion object {
        const val TAXA_CHECKIN_ANTECIPADO = 60.0
    }

    fun calcularDiarias(): Double = quantidadeDias * quarto.valorDiaria

    fun calcularTaxaCheckIn(): Double {
        return if (checkInAntecipado) {
            TAXA_CHECKIN_ANTECIPADO
        } else {
            0.0
        }
    }

    fun somarDespesasExtras(): Double {
        var total = 0.0
        for (despesa in despesasExtras) {
            total += despesa
        }
        return total
    }

    fun pedidoEspecialFormatado(): String =
        hospede.pedidoEspecial ?: "Nenhum pedido especial"

    fun calcularTotal(): Double {
        return calcularDiarias() + calcularTaxaCheckIn() + somarDespesasExtras()
    }

    fun imprimirResumo() {
        println("========= RESUMO DA RESERVA =========")
        println("Hóspede...........: ${hospede.nome}")
        println("CPF...............: ${hospede.cpf}")
        println("Quarto nº.........: ${quarto.numero}")
        println("Tipo do quarto....: ${quarto.tipo}")
        println("Vantagens.........: ${quarto.vantagensExtras()}")
        println("Diárias (${quantidadeDias} x R$ %.2f): R$ %.2f"
            .format(quarto.valorDiaria, calcularDiarias()))
        println("Check-in antecipado: ${if (checkInAntecipado) "Sim" else "Não"}")
        println("Taxa check-in.....: R$ %.2f".format(calcularTaxaCheckIn()))
        println("Despesas extras...: R$ %.2f".format(somarDespesasExtras()))
        println("Pedido especial...: ${pedidoEspecialFormatado()}")
        println("-------------------------------------")
        println("TOTAL GERAL.......: R$ %.2f".format(calcularTotal()))
        println("=====================================\n")
    }
}

fun main() {

    val hospede1 = Hospede(
        nome = "Ana Souza",
        cpf = "123.456.789-00",
        pedidoEspecial = "Quarto com berço para bebê"
    )

    val hospede2 = Hospede(
        nome = "Carlos Lima",
        cpf = "987.654.321-00"
    )

    val hospede3 = Hospede(
        nome = "Arthur Silva",
        cpf = "283.157.234-00"
    )

    val quartoStandard = Quarto(numero = 101, tipo = "Standard",      valorDiaria = 150.0)
    val quartoLuxo     = Quarto(numero = 202, tipo = "Luxo",          valorDiaria = 320.0)
    val quartoPres     = Quarto(numero = 303, tipo = "Presidencial",  valorDiaria = 850.0)

    val reserva1 = Reserva(
        hospede = hospede1,
        quarto = quartoLuxo,
        quantidadeDias = 4,
        checkInAntecipado = true,
        despesasExtras = listOf(45.0, 90.0, 12.50)
    )

    val reserva2 = Reserva(
        hospede = hospede2,
        quarto = quartoStandard,
        quantidadeDias = 2,
        checkInAntecipado = false,
        despesasExtras = listOf(20.0)
    )

    val reserva3 = Reserva(
        hospede = hospede3,
        quarto = quartoPres,
        quantidadeDias = 3,
        checkInAntecipado = true
    )

    reserva1.imprimirResumo()
    reserva2.imprimirResumo()
    reserva3.imprimirResumo()
}