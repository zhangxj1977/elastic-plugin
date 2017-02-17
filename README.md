### インデックスマッピング設定
```json
curl -X PUT -H "Content-Type: application/json" 'http://localhost:9200/myindex' -d '
{
  "settings": {
    "analysis" : {
      "analyzer" : {
        "ngram-analyzer" : {
          "type" : "custom",
          "tokenizer" : "ngram-tokenizer",
          "filter" : "ngram"
        }
      },
      "tokenizer": {
        "ngram-tokenizer": {
          "type" : "nGram",
          "min_gram" : "2",
          "max_gram" : "2"
        }
      },
      "filter": {
        "ngram" : {
          "type" : "nGram",
          "min_gram" : "2",
          "max_gram" : "2"
        }
      }
    }
  },
  "mappings": {
    "sample": {
      "properties": {
        "cartype": { 
          "type": "string",
          "index": "not_analyzed",
          "fields": {
            "ngram": { 
              "type":  "string",
              "analyzer": "ngram-analyzer"
            }
          }
        }
      }
    }
  }
}
```
