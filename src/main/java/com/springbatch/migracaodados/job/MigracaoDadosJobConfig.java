package com.springbatch.migracaodados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job migracaoDadosJob(
            Step migrarPessoaStep,
            Step migrarDadosBancariosStep
    ) {
        return jobBuilderFactory
                .get("migracaoDadosJob")
                .start(migrarPessoaStep)
                .next(migrarDadosBancariosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
