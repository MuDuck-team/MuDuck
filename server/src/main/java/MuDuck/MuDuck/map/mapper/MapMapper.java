package MuDuck.MuDuck.map.mapper;

import MuDuck.MuDuck.map.dto.AvgDto;
import MuDuck.MuDuck.map.entity.AvgEntity;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapMapper {
    @Mapping(target = "id", source = "mapId")
    @Mapping(target = "name", source = "placeName")
    @Mapping(target = "categoryGroupCode", source = "categoryGroupCode.code")
    AvgDto.Response avgEntityToResponse(AvgEntity avgEntity);

    List<AvgDto.Response> listAvgEntityToListResponse(List<AvgEntity> avgEntities);

    default Map<String, List<AvgDto.Response>> mapAvgEntity(Map<GroupCode, List<AvgEntity>> groupCodeListMap){
        if (groupCodeListMap == null) {
            return null;
        } else {
            Map<String, List<AvgDto.Response>> map = new LinkedHashMap<>();

            for (Entry<GroupCode, List<AvgEntity>> groupCodeListEntry : groupCodeListMap.entrySet()) {
                Entry<GroupCode, List<AvgEntity>> entry = groupCodeListEntry;
                String key = (entry.getKey()).getName();
                List<AvgDto.Response> value = this.listAvgEntityToListResponse(
                        entry.getValue());
                map.put(key, value);
            }

            return map;
        }
    };

}
