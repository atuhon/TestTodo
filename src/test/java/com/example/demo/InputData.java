package com.example.demo;
import java.io.IOException;
import java.io.InputStream;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;

import org.springframework.core.io.Resource;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class InputData extends AbstractDataSetLoader {
    @Override
    protected IDataSet createDataSet(Resource resource) throws IOException, DataSetException {
        try (InputStream inputStream = resource.getInputStream()) {
            return new XlsDataSet(inputStream);
        }
    }
}
