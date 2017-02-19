### インデックスマッピング設定
```json
curl -X POST -H "Content-Type: application/json" 'http://192.168.99.1:9200/myindex' -d '
{
  "settings": {
    "analysis" : {
      "analyzer" : {
        "ngram-analyzer" : {
          "type" : "custom",
          "tokenizer" : "ngram-tokenizer",
          "filter" : "encrypt"
        }
      },
      "tokenizer": {
        "ngram-tokenizer": {
          "type" : "nGram",
          "min_gram" : "1",
          "max_gram" : "1"
        }
      },
      "filter": {
        "encrypt" : {
          "type" : "encrypt"
        }
      }
    }
  },
  "mappings": {
    "sample": {
      "properties": {
        "name": {
          "type": "string",
          "analyzer": "ngram-analyzer"
        }
      }
    }
  }
}'
```

### インデックスデータを作成
```json
curl -XPUT 'http://192.168.99.1:9200/myindex/sample/1' -d '
{
  "name": "田中　一郎"
}'
```

### インデックスデータを見る
```json
curl http://192.168.99.1:9200/_search?pretty
{
  "took" : 7,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 1.0,
    "hits" : [ {
      "_index" : "myindex",
      "_type" : "sample",
      "_id" : "1",
      "_score" : 1.0,
      "_source" : {
        "name" : "田中　一郎"
      }
    } ]
  }
}'
```


curl -XDELETE 'http://192.168.99.1:9200/myindex/sample/1'

curl -XPUT 'http://192.168.99.1:9200/myindex/sample/1' -d '
{
  "name": "田中一郎"
}'

curl http://192.168.99.1:9200/myindex/sample/_search?pretty -d '
{
  "query": { 
    "bool": { 
      "must": [
        {"match" : { "name" : "田中"}}
      ]
    }
  }
}'

curl -XGET 'http://192.168.99.1:9200/myindex/_analyze?pretty' -d '
{
  "analyzer" : "ngram-analyzer",
  "text" : "田中一郎",
  "explain" : true
}'

curl -XPUT 'http://192.168.99.1:9200/myindex/sample/2' -d '
{
  "name": "zhang xuejun"
}'
curl -XGET http://192.168.99.1:9200/myindex/sample/_search?pretty -d '
{
  "query": { 
    "bool": { 
      "must": [
        {"match" : { "name" : "zhang"}}
      ]
    }
  },
  "explain" : true
}'

curl -XGET 'http://192.168.99.1:9200/myindex/_analyze?pretty' -d '
{
  "analyzer" : "ngram-analyzer",
  "text" : "zhang",
  "explain" : true
}'
