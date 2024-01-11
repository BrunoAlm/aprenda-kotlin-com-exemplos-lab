import java.lang.IllegalArgumentException

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

class UsuarioJaExisteException (message: String): Throwable(message)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario (val nome: String, val sobrenome: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
            println("${usuario.nome} ${usuario.sobrenome} foi matriculado na formação $nome.")
        } else {
            throw UsuarioJaExisteException("${usuario.nome} ${usuario.sobrenome} já está matriculado na formação $nome.")
        }
    }
}

fun main() {
    val usuario1 = Usuario("João", "Fulano")
    val usuario2 = Usuario("Maria", "Fulana")

    val conteudo1 = ConteudoEducacional("Introdução à Kotlin", 10, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Bancos de Dados SQL e NoSQL", 20, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Kotlin Backend Spring Boot", 30, Nivel.AVANCADO)

    val formacao = Formacao("Ciência da Computação", listOf(conteudo1, conteudo2, conteudo3))

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)
    formacao.matricular(usuario1) // Tentar adicionar um usuário já matriculado

    println("Inscritos na formação ${formacao.nome}: ${formacao.inscritos.joinToString(", ") { it.nome }}")
    println("Conteúdos dessa formação: ${formacao.conteudos.joinToString(", ") { it.nome + " de nível " + it.nivel }}")
}
