# TODO
## 簡単そうなもの
- YAMLをコメント付きでパースできるようにし, `MessagePicker`を生成する際にKDocとして付加する.
- セリフファイルの新形式が アクション名 -> ゆっくりタイプ -> セリフリスト であることをどこかに書く.
- セリフ編集がしやすくなるのなら, 野良のセリフも統合する.

## 大変そうなもの
- 似てるものが隣接するようにセリフを並び替える.
- セリフデータの正しくないコメントを修正する.
- セリフデータを対象のゆっくり毎に作らない（母や父など）
- まりさをだぜまりさと普通の口調とゲスに分ける（現在はゲスとだぜまりさが区別されていない）
- セリフ名がセリフの種類じゃなくセリフの条件になっていると流用しづらいので、できるだけセリフの種類にする
- hasOkurumiの場合うんうんをするときのセリフがおくるみを汚してしまったセリフに変わるなど、状態によってセリフの種類そのものが変わってしまっているものを分割する