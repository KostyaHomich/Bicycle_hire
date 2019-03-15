package epam.project.builder;


import epam.project.entity.PointHire;

import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Map;

public class PointHireBuilder  implements Builder<PointHire> {

    private static final String ID = "id";
    private static final String LOCATION = "location";
    private static final String TELEPHONE = "telephone";
    private static final String DESCRIPTION = "description";

    private static final Logger LOGGER = LogManager.getLogger(PointHireBuilder.class);


    @Override
    public PointHire build(Map<String, String> params) throws ServiceException {

        PointHire pointHire = new PointHire();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case ID:
                    pointHire.setId(Integer.valueOf(value));
                    break;
                case LOCATION:
                    pointHire.setLocation(value);
                    break;
                case TELEPHONE:
                    pointHire.setTelephone(value);
                    break;
                case DESCRIPTION:
                    pointHire.setDescription(value);
                    break;
                default:
                    break;
            }

        }

        return pointHire;

    }

}
