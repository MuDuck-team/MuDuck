package MuDuck.MuDuck.map.mapper;

import MuDuck.MuDuck.map.dto.AvgDto;
import MuDuck.MuDuck.map.dto.MapDto;
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
    default AvgDto.Response avgEntityToResponse(AvgEntity avgEntity){
        if (avgEntity == null) {
            return null;
        } else {
            long id = avgEntity.getMapId();
            String name = avgEntity.getPlaceName();
            String categoryGroupCode = this.avgEntityCategoryGroupCodeCode(avgEntity);
            long placeId = avgEntity.getPlaceId();
            String address = avgEntity.getAddress();
            String roadAddress = avgEntity.getRoadAddress();
            String phone = avgEntity.getPhone();
            double avgScore = Math.round(avgEntity.getAvgScore() * 100) / 100.0;
            int totalReviews = avgEntity.getTotalReviews();
            double longitude = avgEntity.getLongitude();
            double latitude = avgEntity.getLatitude();
            String placeUrl = avgEntity.getPlaceUrl();

            AvgDto.Response response = new AvgDto.Response(id, placeId, name, address, roadAddress, phone, avgScore, totalReviews, longitude, latitude, placeUrl, categoryGroupCode);
            return response;
        }
    };

    private String avgEntityCategoryGroupCodeCode(AvgEntity avgEntity) {
        if (avgEntity == null) {
            return null;
        } else {
            GroupCode categoryGroupCode = avgEntity.getCategoryGroupCode();
            if (categoryGroupCode == null) {
                return null;
            } else {
                String code = categoryGroupCode.getCode();
                return code == null ? null : code;
            }
        }
    }

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
    @Mapping(target="theater.theaterId", source = "theaterId")
    MuDuck.MuDuck.map.entity.Map postMapToMap(MapDto.Post post);

}
