executeRunnable:

- handleValidPhase 部署组件第一个input check 评估 input

- deploy_History 表 所有的部署历史,一个 TransactionId 可能对应多个 deployVersion

|deploy_transaction_id    |deploy_version    |content_deploy_list    |deploy_time|
|---|---|---|---|

- deploy_config 表, 实际的部署配置,只能对应唯一的transactionId

|deploy_transaction_id    |content_deploy_list    |deploy_ops_id    |from_job|
|---|---|---|---|

- deploy_service_provider 表

|service_name    |content_type    |content_info_settings    |service_provider_id    |service_provider_desp    |service_provider_settings    |group_name    |model_type    |model_limit|
|---|---|---|---|---|---|---|---|---|

- content_deploy_config 表 内容部署功能表: 每一个提交到服务的内容 和 对应的服务提供者

|   content_name    |content_type    |deploy_service_provider    |deploy_transaction_id |    ext_config|    alert_status|
|---|---|---|---|---|---|


部署相关文件 oss 同步到 idc 
示例: http://ops.ximalaya.com/droplet/offline/deploy_%E5%8F%8C%E5%86%99%E4%BB%BB%E5%8A%A1
模型文件:
outputDataset -> fileInfo -> path
/reslib/droplet/model/warehouse/MTL/1.56/

字典文件(老的特征组件里配置的):
outputDataset ->featureConfigOutput -> configData -> dictInfos -> dynamicDictResult 
eg: /reslib/droplet/generate/data/94434762/1625931911500/FEkqalboja/search_word_dic


