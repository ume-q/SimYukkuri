package messageutil

// 要素が増えるようならSetを使って実装する.
/**
 * セリフに関わるゆっくりの状態のタイプ.
 *
 * @param love プレイヤーに対する愛情.
 */
data class Type(val growth: Growth, val isImmoral: Boolean, val isDamaged: Boolean, val isUnunSlave: Boolean, val love: Love, val hasOkurumi: Boolean) {
    constructor(): this(Growth.ALL, false, false, false, Love.ALL, false)

    override fun toString(): String {
        return "Type(${this.toSimpleString()})"
    }

    /** 型や括弧を含まない文字列表現を返す. */
    fun toSimpleString(): String {
        val builder = StringBuilder()
        builder.append("$growth")
        if (isImmoral)
            builder.append(", $immoral")
        if (isDamaged)
            builder.append(", $damage")
        if (isUnunSlave)
            builder.append(", $pooSlave")
        if (love != Love.ALL)
            builder.append(", $love")
        if (hasOkurumi)
            builder.append(", $wrapper")
        return builder.toString()
    }

    companion object {
        // 各状態の文字列表現
        val immoral = "immoral"
        val damage = "damage"
        val pooSlave = "pooSlave"
        val wrapper = "wrapper"

        /**
         *  文字列を[Type]オブジェクトとしてパースする.
         *
         *  属性の順序は考慮されない. [growth]だけが必須.
         */
        fun parse(s: String): Type {
            val growth = when {
                s.contains(Growth.BABY_OR_CHILD.toString()) -> Growth.BABY_OR_CHILD
                s.contains(Growth.BABY.toString()) -> Growth.BABY
                s.contains(Growth.CHILD.toString()) -> Growth.CHILD
                s.contains(Growth.ADULT.toString()) -> Growth.ADULT
                else -> Growth.ALL
            }
            val isImmoral = s.contains(immoral)
            val isDamaged = s.contains(damage)
            val isPoopSlave = s.contains(pooSlave)
            val love = when {
                s.contains(Love.HATE.toString()) -> Love.HATE
                s.contains(Love.LOVE.toString()) -> Love.LOVE
                else -> Love.ALL
            }
            val hasWrapper = s.contains(wrapper)
            return Type(growth, isImmoral, isDamaged, isPoopSlave, love, hasWrapper)
        }
    }
}


/** ゆっくりの成長段階. */
enum class Growth {
    BABY_OR_CHILD { override fun toString(): String = "babyOrChild" },
    BABY { override fun toString(): String = "baby" },
    CHILD { override fun toString(): String = "child" },
    ADULT { override fun toString(): String = "adult" },
    ALL { override fun toString(): String = "all" };

    companion object {
        fun parse(s: String): Growth {
            return when (s) {
                BABY_OR_CHILD.toString() -> BABY_OR_CHILD
                BABY.toString() -> BABY
                CHILD.toString() -> CHILD
                ADULT.toString() -> ADULT
                ALL.toString() -> ALL
                else -> throw Exception("For input string: $s")
            }
        }
    }
}


/** ゆっくりのプレイヤーへの愛情度. */
enum class Love {
    HATE { override fun toString(): String = "hate" },
    LOVE { override fun toString(): String = "love" },
    ALL { override fun toString(): String = "all" }
}