/**
 * 获取成功之后的数据
 */
export const parsed = (data) => {
  if (data.statusCode === 200) {
    return data.message;
  }
};
