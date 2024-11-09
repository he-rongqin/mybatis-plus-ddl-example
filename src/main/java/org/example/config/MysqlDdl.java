package org.example.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.ddl.IDdl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author herongqin
 * @date 2024/11/9 23:51
 **/
@Component
@Slf4j
public class MysqlDdl implements IDdl {
    private final DataSource dataSource;

    @Autowired
    public MysqlDdl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Value("${mybatis-plus.ddl.dir.mysql:db/mysql}")
    private String dir;

    @Override
    public void runScript(Consumer<DataSource> consumer) {
        consumer.accept(dataSource);
    }

    @Override
    public List<String> getSqlFiles() {
        File file = FileUtil.file(dir);
        if (file.isDirectory()) {
            return Arrays.stream(file.listFiles()).map(t -> StrUtil.join("/", dir, t.getName())).toList();

        }
        return null;
    }
}
