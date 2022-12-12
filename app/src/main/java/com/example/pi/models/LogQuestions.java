package com.example.pi.models;

public class LogQuestions {

    public static String question[] = {
            ///Perguntas
            "O que é um mapa mental?",
            "Oque é um organograma?",
            "São tipos de recrutamento e seleção:",
            "Qual a jornada de trabalho de um Jovem Aprendiz?",
            "Qual a idade do jovem Aprendiz?",
            "As leis trabalhistas sempre foram as mesmas?"
    };

    public static String choices[][] = {
            ///escolhas
            {"Um tipo de mapeamento para recrutar pessoas", "É um teste mental usado pelas organizações com seus colaboradores", "São diagramas que relacionam diferentes informações a uma fonte central", "Uma técnica de vendas"},
            {"Ilustra as etapas, sequências e decisões de um processo ou fluxo de trabalho", "Uma ilustração estratégica com o intuito de auxiliar clientes", "É uma representação gráfica da divisão hierárquica e da relação superior-subordinado", "Um gráfico com todos colaboradores de determinada organização"},
            {"Externo, interno e misto", "Interno, simulado, não estruturado", "Externo, coletivo, estruturado", "Interno, teórico, misto"},
            {"Para os aprendizes que estão cursando o ensino fundamental será seis horas diárias.Já para os que completaram o ensino médio será até 8 horas diárias", "Para os aprendizes que estão cursando o ensino fundamental será uma carga horária de 8 horas. Já os que completaram o ensino médio será de 6 horas diárias", "Varia dependendo da idade do aprendiz", "8 horas diárias"},
            {"13 a 23 anos", "14 a 18 anos", "15 a 21 anos", "14 a 24 anos"}
    };

    public static String correctAnswers[] = {
            ///respotas certas
            "São diagramas que relacionam diferentes informações a uma fonte central",
            "É uma representação gráfica da divisão hierárquica e da relação superior-subordinado",
            "Externo, interno e misto",
            "Para os aprendizes que estão cursando o ensino fundamental será seis horas diárias.Já para os que completaram o ensino médio será até 8 horas diárias",
            "14 a 24 anos"
    };

    public LogQuestions(){}
}
