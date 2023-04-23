```sql
CREATE TABLE `train_component_param_collect`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user`           varchar(255)  NOT NULL DEFAULT '' COMMENT '用户',
    `job`            varchar(255)  NOT NULL DEFAULT '' COMMENT '实验名',
    `component_name` varchar(255)  NOT NULL DEFAULT '' COMMENT '组件名',
    `start_time`     varchar(128)  NOT NULL DEFAULT '' COMMENT '开始时间',
    `end_time`       varchar(128)  NOT NULL DEFAULT '' COMMENT '结束时间',
    `elapsed_time`   int unsigned NOT NULL COMMENT '总耗时秒',
    `type`           varchar(50)   NOT NULL DEFAULT '' COMMENT '任务运行类型,cron,手动,补数',
    `sample_num`     int unsigned NOT NULL COMMENT '样本量',
    `epoch`          smallint unsigned NOT NULL COMMENT 'epoch',
    `batchsize`      int unsigned NOT NULL COMMENT 'batchsize',
    `feature_num`    smallint unsigned NOT NULL DEFAULT '0' COMMENT '特征个数',
    `feature_len`    smallint unsigned NOT NULL DEFAULT '0' COMMENT '特征总长',
    `feature_names`  varchar(5000) NOT NULL COMMENT 'feature names',
    `model_info`     varchar(255)  NOT NULL DEFAULT '' COMMENT '模型info',
    `extra_info`     varchar(5000) NOT NULL DEFAULT '' COMMENT 'extra_info',
    `gmt_create`     datetime      NOT NULL COMMENT '创建时间',
    `gmt_modified`   datetime      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY              `idx_start_time` (`start_time`),
    KEY              `idx_end_time` (`end_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = COMPACT COMMENT = '训练组件数据收集'
```

```sql
CREATE TABLE `online_trait_collect`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user`           varchar(255) NOT NULL DEFAULT '' COMMENT '用户',
    `group`          varchar(255) NOT NULL DEFAULT '' COMMENT '用户组',
    `job`            varchar(255) NOT NULL DEFAULT '' COMMENT '实验名',
    `component_name` varchar(255) NOT NULL DEFAULT '' COMMENT '组件名',
    `gmt_create`     datetime      NOT NULL COMMENT '创建时间',
    `gmt_modified`   datetime      NOT NULL COMMENT '更新时间',
    `deploy_exist`   tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否存在部署组件',
    PRIMARY KEY (`id`),
    KEY              `idx_start_time` (`start_time`),
    KEY              `idx_end_time` (`end_time`)

) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = COMPACT COMMENT = '实时日志画像收集'
```
