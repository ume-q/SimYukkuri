package messageutil

import java.nio.file.Files
import java.nio.file.Path

/** メッセージを取り出すクラスの名前 */
val messageClassName = "MessagePicker"

// TODO: ダメージを受けている版のメッセージがない場合, 通常版のメッセージで代用するなど欠けを補う.
/** メッセージのプロパティ部分のソースを生成する. */
fun messageCollectionToProperties(msgData: Map<String, Any?>): String = buildString {
    for ((messageName) in msgData) {
        appendln("val $messageName: String?")
        appendln("    get() = messageCollection[\"$messageName\"]?.get(self.messageCondition)?.randomElement()")
    }
}

/** 指定されたセリフデータからMessagesクラスのソースファイルを作成し, [writeDir]に保存する. */
fun writeClassSource(writeDir: Path, vararg messagePaths: Path) {
    val srcString = run {
        when (messagePaths.size) {
            0 -> ""
            1 -> Files.newInputStream(messagePaths[0])
                .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Condition, List<String>>>) }
                .let { messageCollectionToProperties(it) }
            else -> run {
                val messageData = Files.newInputStream(messagePaths[0])
                    .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Condition, List<String>>>) }
                for (i in 1 until messagePaths.size) {
                    Files.newInputStream(messagePaths[i])
                        .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, Map<Condition, List<String>>>) }
                        .let { messageData.putAll(it) }
                }
                messageCollectionToProperties(messageData)
            }
        }
    }

    val path = writeDir.resolve("$messageClassName.kt")
    Files.newBufferedWriter(path).use {
        it.write(srcString)
    }
}