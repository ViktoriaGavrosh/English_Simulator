package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.model.ModelName
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.model.dbmodel.DialogDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.WordDb

/**
 * Converts [SentenceDb] instance to [Question] instance
 *
 * @return [Question] instance
 */
internal fun SentenceDb.toQuestion(): Question {
    return Question.Sentence(
        id = id,
        questionText = enText,
        answerText = ruText,
    )
}

/**
 * Converts [IssueDb] instance to [Question] instance
 *
 * @return [Question] instance
 */
internal fun IssueDb.toQuestion(): Question {
    return Question.Issue(
        id = id,
        questionText = englishQuestion,
        answerText = russianQuestion,
        theme = theme,
    )
}

/**
 * Converts [DialogDb] instance to [Question] instance
 *
 * @return [Question] instance
 */
internal fun DialogDb.toQuestion(): Question {
    return Question.Dialog(
        id = id,
        questionText = question,
        answerText = shortAnswer,
    )
}

/**
 * Converts [WordDb] instance to [Question] instance
 *
 * @return [Question] instance
 */
internal fun WordDb.toQuestion(): Question {
    return Question.Word(
        id = id,
        questionText = englishWord,
        answerText = russianWord,
        theme = theme,
    )
}

/**
 * Converts [Question.Word] instance to [WordDb] instance for repository
 *
 * @return [WordDb] instance
 */
internal fun Question.Word.toWordDb(): WordDb {
    return WordDb(
        id = id,
        englishWord = questionText,
        russianWord = answerText,
        theme = theme,
    )
}

/**
 * Converts [Question] instance to [GameQuestionUi] instance for ui (GameScreen)
 *
 * @return [GameQuestionUi] instance
 */
fun Question.toGameQuestionUi(isToEnglish: Boolean = false): GameQuestionUi {
    val question = if (isToEnglish) answerText else questionText
    val translate = if (isToEnglish) questionText else answerText

    return GameQuestionUi(
        id = id,
        question = question,
        translate = translate,
    )
}

/**
 * Converts [GameQuestionUi] instance to [Question] instance for ui (GameScreen)
 *
 * @param theme theme of issue
 * @return [Question] instance
 */
fun GameQuestionUi.toQuestion(
    theme: String = "",
    isToEnglish: Boolean = true,
    modelName: ModelName = ModelName.Word
): Question {
    val ruText = if (isToEnglish) question else translate
    val enText = if (isToEnglish) translate else question

    return when (modelName) {
        ModelName.Sentence -> Question.Sentence(
            id = id,
            questionText = enText,
            answerText = ruText,
        )

        ModelName.Issue -> Question.Issue(
            id = id,
            questionText = enText,
            answerText = ruText,
            theme = theme,
        )

        ModelName.Dialog -> Question.Dialog(
            id = id,
            questionText = enText,
            answerText = ruText,
        )

        ModelName.Word -> Question.Word(
            id = id,
            questionText = enText,
            answerText = ruText,
            theme = theme,
        )
    }
}
